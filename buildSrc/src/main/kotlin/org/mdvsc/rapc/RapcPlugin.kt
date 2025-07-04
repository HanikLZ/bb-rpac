package org.mdvsc.rapc

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import proguard.gradle.ProGuardTask
import java.io.File

class RapcPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        if (!target.plugins.hasPlugin("java")) return
        val gradle = target.gradle
        val sdkServiceProvider = gradle.sharedServices.registerIfAbsent("rapcLibraryService", RapcLibraryService::class.java) {
            parameters.gradleUserHome.set(gradle.gradleUserHomeDir)
        }
        target.dependencies.add("compileOnly", sdkServiceProvider.map { target.files(it.sdkJarFile) })
        val extension = target.extensions.create("rapc", RapcExtension::class.java)
        val jarTask = target.tasks.named("jar", Jar::class.java)
        val prepare = target.registerPatchTask("assembleJar", "build") {
            sourceFiles.from(jarTask)
            outputFile.set(jarTask.singleFile("prepare"))
        }
        val sourceSets = target.extensions.getByType(SourceSetContainer::class.java)
        val shrunk = target.assembleShrunkFatJar("shrunkJar", "build", prepare, sourceSets)
        target.applyRapc(shrunk, sdkServiceProvider, sourceSets, extension)
        target.applyMidlet(shrunk, sdkServiceProvider, sourceSets, extension)
        target.configureJavaCompatibility()
    }

    private fun Project.applyRapc(prepare: TaskProvider<ProGuardTask>,
                                  service: Provider<RapcLibraryService>,
                                  sourceSets: SourceSetContainer,
                                  extension: RapcExtension) {
        val group = GROUP_RAPC
        val adapted = registerPatchTask("${group}AdaptJar", group = group) {
            sourceFiles.from(prepare.map { it.outJarFileCollection })
            outputFile.set(prepare.singleFile("adapt", name = group))
        }
        val final = finalizeAndObfuscateJar("${group}ObfuscateJar", group, adapted, sourceSets, provider { extension.app.midlet })
        val app = provider { extension.app }
        val generateRapcConfig = tasks.register("rapcFile", RapcGenerateTask::class.java) {
            this.group = group
            appConfig.set(app)
            destPath.set(extension.codOutputPath)
            jarFile.set(final.singleFile())
        }
        val rapc = tasks.register("rapc", RapcTask::class.java) {
            this.group = group
            quiet.set(extension.quiet)
            midlet.set(app.map { it.midlet })
            library.set(app.map { it.library })
            rapcFile.set(generateRapcConfig.flatMap { it.rapcFile })
            rimApiLibFile.set(project.layout.file(service.map { it.sdkJarFile }))
            sourceFiles.from(final.map { it.outJarFileCollection })
        }
        tasks.register("rapcDeploy", Exec::class.java) {
            this.group = group
            dependsOn(rapc)
            executable(extension.javaLoader)
            argumentProviders.add {
                val filePath = rapc.flatMap { it.codFile.asFile }.get().absolutePath
                listOf("-u", "load", "$filePath.cod")
            }
        }
    }

    private fun Project.applyMidlet(prepare: TaskProvider<ProGuardTask>,
                                    service: Provider<RapcLibraryService>,
                                    sourceSets: SourceSetContainer,
                                    extension: RapcExtension) {
        val group = GROUP_MIDLET
        val adapted = registerPatchTask("${group}AdaptJar", group = group) {
            sourceFiles.from(prepare.map { it.outJarFileCollection })
            outputFile.set(prepare.singleFile("adapt", name = group))
        }
        val final = finalizeAndObfuscateJar("${group}ObfuscateJar", group, adapted, sourceSets, provider { true })
        val app = provider { extension.app }
        val midletJad = tasks.register("${group}Jad", RapcGenerateTask::class.java) {
            this.group = group
            appConfig.set(app)
            destPath.set(extension.midletOutputPath)
            jad.set(true)
            jarFile.set(final.singleFile())
        }
        val midlet = tasks.register(group, Jar::class.java) {
            this.group = group
            dependsOn(midletJad)
            archiveClassifier.set(group)
            archiveVersion.set(null)
            destinationDirectory.set(extension.midletOutputPath)
            val mf = midletJad.flatMap { it.rapcFile.asFile }
            archiveFileName.set(mf.map { "${it.nameWithoutExtension}.jar" })
            manifest.from(mf) {
                eachEntry {
                    if (key.startsWith("RIM-")
                        || key == "MIDlet-Jar-URL"
                        || key == "MIDlet-Jar-Size") exclude()
                }
            }
            from(final.map { it.outJarFileCollection.map(::zipTree) })
        }
        tasks.register("${group}Sign", RapcJadSignTask::class.java) {
            this.group = group
            jarUrl.set(extension.app.jarUrl)
            jarFile.set(midlet.singleFile())
            keystore.set(layout.file(service.map { it.keyStoreFile }))
            jadFile.set(midletJad.flatMap { it.rapcFile })
        }
    }

    private inline fun Project.registerProGuardTask(name: String,
                                                    group: String,
                                                    crossinline task: ProGuardTask.() -> Unit) =
        tasks.register(name, ProGuardTask::class.java) {
            this.group = group
            task()
        }

    private inline fun Project.registerPatchTask(name: String,
                                                 group: String = GROUP_RAPC,
                                                 crossinline task: RapcPatchTask.() -> Unit) = tasks.register(name, RapcPatchTask::class.java) {
        this.group = group
        task()
    }

    private val Project.libPath get() = File(projectDir, "libs").absolutePath
    private fun File.isKotlinStdlib() = name.startsWith("kotlin-stdlib")
    private fun File.inLibs(libPath: String) = parentFile.absolutePath.startsWith(libPath)
    private fun TaskProvider<out Task>.singleFile(suffix: String? = null,
                                                  prefix: String? = null,
                                                  name: String? = null,
                                                  sep: String = "-") = map {
        val file = it.outputs.files.singleFile
        val projectDirectory = it.project.layout.projectDirectory
        if (suffix != null || prefix != null) {
            val name = listOfNotNull(
                prefix,
                name ?: file.nameWithoutExtension,
                suffix).joinToString(sep)
            val ext = file.extension.ifBlank { null }
            File(file.parentFile, ext?.run { "$name.$this" } ?: name)
            projectDirectory
                .dir(file.parentFile.relativeTo(it.project.projectDir).path)
                .file(ext?.run { "$name.$this" } ?: name)
        } else projectDirectory.file(file.relativeTo(it.project.projectDir).path)
    }
    private fun SourceSetContainer.includeLibrary(libPath: String) = main.map { it.compileClasspath.filter { file -> file.isKotlinStdlib() || file.inLibs(libPath) } }
    private fun SourceSetContainer.extLibrary(libPath: String) = main.map { it.compileClasspath.filter { file -> !file.isKotlinStdlib() && !file.inLibs(libPath)  } }
    private val SourceSetContainer.main get() = named("main", SourceSet::class.java)


    private fun Project.assembleShrunkFatJar(taskName: String,
                                             group: String,
                                             rapcPatch: TaskProvider<RapcPatchTask>,
                                             sourceSets: SourceSetContainer)
    = registerProGuardTask(taskName, group) {
        val libPath = libPath
        injars(rapcPatch.map { it.outputFile })
        injars(mapOf("filter" to "!META-INF/MANIFEST.MF"), sourceSets.includeLibrary(libPath))
        libraryjars(sourceSets.extLibrary(libPath))
        outjars( mapOf("filter" to "!**/*.kotlin_*"), rapcPatch.singleFile("min"))
        verbose()
        dontwarn()
        dontobfuscate()
        optimizationpasses(3)
        allowaccessmodification()
        assumenosideeffects(PROGUARD_REMOVE_NULL)
        keepclasseswithmembers(PROGUARD_KEEP_MIDLET)
        keepclasseswithmembers(PROGUARD_KEEP_MAIN)
    }

    private fun Project.finalizeAndObfuscateJar(taskName: String,
                                                group: String,
                                                rapcPatch: TaskProvider<RapcPatchTask>,
                                                sourceSets: SourceSetContainer,
                                                midlet: Provider<Boolean>) = registerProGuardTask(taskName, group) {
        val libPath = File(projectDir, "libs").absolutePath
        fun File.isKotlinStdlib() = name.startsWith("kotlin-stdlib")
        fun File.inLibs() = parentFile.absolutePath == libPath
        injars(rapcPatch.map { it.outputFile })
        libraryjars(sourceSets.main.map { it.compileClasspath.filter { file -> !file.isKotlinStdlib() && !file.inLibs()  } })
        outjars(rapcPatch.singleFile("obx"))
        verbose()
        dontwarn()
        repackageclasses("")
        allowaccessmodification()
        doFirst {
            val midletMode = midlet.get()
            if (midletMode) {
                target("1.3")
                microedition()
            } else target("1.6")
            keepclasseswithmembers(if (midletMode) PROGUARD_KEEP_MIDLET else PROGUARD_KEEP_MAIN)
        }

    }

    private fun Project.configureJavaCompatibility() = afterEvaluate {
        if (plugins.hasPlugin("java") || plugins.hasPlugin("java-library")) {
            val maxVersion = JavaVersion.VERSION_11
            val maxVersionCode = 11
            val javaExtension = extensions.getByType(JavaPluginExtension::class.java)
            if (javaExtension.sourceCompatibility > maxVersion) {
                javaExtension.sourceCompatibility = maxVersion
                logger.warn("[${this.javaClass.simpleName}] Warning: Java source change to $maxVersion")
            }
            if (javaExtension.targetCompatibility > maxVersion) {
                javaExtension.targetCompatibility = maxVersion
                extensions.getByType(JavaCompile::class.java).options.release.set(maxVersionCode)
                logger.warn("[${this.javaClass.simpleName}] Warning: Java target change to $maxVersion")
            }
        }
        if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
            val maxJvmTarget = JvmTarget.JVM_11
            val kotlinCompile = extensions.getByType(KotlinJvmExtension::class.java).compilerOptions
            val currentVersion = kotlinCompile.jvmTarget.get()
            if (kotlinCompile.jvmDefault.orNull != JvmDefaultMode.DISABLE) {
                kotlinCompile.jvmDefault.set(JvmDefaultMode.DISABLE)
                logger.warn("[${this.javaClass.simpleName}] Warning: Kotlin JvmDefault change to DISABLE")
            }
            if (currentVersion > maxJvmTarget) {
                kotlinCompile.jvmTarget.set(maxJvmTarget)
                logger.warn("[${this.javaClass.simpleName}] Warning: Kotlin JvmTarget change to $maxJvmTarget")
            }
        }
    }
}