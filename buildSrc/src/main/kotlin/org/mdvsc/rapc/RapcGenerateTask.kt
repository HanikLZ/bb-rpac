package org.mdvsc.rapc

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.mdvsc.rapc.data.App
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import java.io.BufferedWriter
import java.io.File
import java.util.jar.JarFile
import kotlin.text.endsWith

abstract class RapcGenerateTask: DefaultTask() {

    @get:Nested
    abstract val appConfig: Property<App>

    @get:InputFile
    abstract val jarFile: RegularFileProperty

    @get:InputDirectory
    abstract val destPath: DirectoryProperty

    @get:OutputFile
    abstract val rapcFile: RegularFileProperty

    @get:Input
    abstract val jad: Property<Boolean>

    init {
        jad.convention(false)
        rapcFile.convention(jad.flatMap { jad ->
            destPath.zip(appConfig) { dir, app ->
                val fileName = app.distName ?: app.name
                val file = dir.asFile
                if (!file.exists()) file.mkdirs()
                dir.file("${fileName.safeFileName()}.${if (jad) "jad" else "rapc"}")
            }
        })
    }

    private fun String.safeFileName(replacement: String = "_"): String =
        this.replace("[\\\\/:*?\"<>|]".toRegex(), replacement)

    private fun BufferedWriter.kv(k: String, v: Any?) {
        if (v != null) {
            write("$k: $v")
            newLine()
        }
    }

    @TaskAction
    fun generate() {
        val app = appConfig.get()
        val jar = jarFile.asFile.get()
        val jad = jad.get()
        val entries = if (app.midlet || jad) jar.findLeafSubclassesInJar("javax/microedition/midlet/MIDlet").toMutableList() else null
        val outputFile = rapcFile.get().asFile
        if (outputFile.exists()) outputFile.delete()
        outputFile.createNewFile()
        outputFile.bufferedWriter(Charsets.UTF_8).use {
            it.kv("MIDlet-Name", app.name)
            it.kv("MIDlet-Vendor", app.vendor)
            it.kv("MIDlet-Description", app.description)
            it.kv("MIDlet-Version", app.version)
            it.kv("MIDlet-Jar-URL", app.jarUrl
                ?.run { if (endsWith(".jar")) this else "$this/${jar.name}" }
                ?:jar.name)
            it.kv("MIDlet-Jar-Size", jar.length())
            it.kv("MicroEdition-Profile", "MIDP-2.0")
            it.kv("MicroEdition-Configuration", "CLDC-1.1")
            app.extras.forEach { (k, v) -> it.kv(k, v) }
            app.entries.forEachIndexed { idx, e ->
                val index = idx + 1
                val arguments = e.arguments?.ifBlank { null }
                    ?.apply { entries?.remove(replace('.', '/')) }
                    ?: entries?.getOrNull(idx)?.replace('/', '.')
                val icon = e.icons.firstOrNull()?.normal ?: run { if (arguments == null) null else "" }
                it.kv("MIDlet-$index", listOfNotNull(
                    e.title ?: app.name, icon, arguments).joinToString(","))
                if (!jad) {
                    it.kv("RIM-MIDlet-Position", e.ribbonPosition)
                    var iconIdx = 0
                    e.icons.forEach { ic ->
                        it.kv("RIM-MIDlet-Icon-$index-${++iconIdx}", ic.normal)
                        ic.focus?.let { fi -> it.kv("RIM-MIDlet-Icon-$index-${++iconIdx}", "$fi,focused") }
                    }
                    if (iconIdx > 0) it.kv("RIM-MIDlet-Icon-Count-$index", iconIdx)
                    var flags = 0
                    if (e.runOnStartup) flags = flags or (0xE1 - ((2 * e.startupTier) shl 4))
                    if (e.systemModule) flags = flags or 0x02
                    it.kv("RIM-MIDlet-Flags-$index", flags)
                }
            }
        }
    }

    private fun File.findLeafSubclassesInJar(targetSuperclassName: String): List<String> {
        val hierarchyMap = mutableMapOf<String, String>()
        val parentClasses = mutableSetOf<String>()
        JarFile(this).use { jarFile ->
            jarFile.entries().asSequence()
                .filter { !it.isDirectory && it.name.endsWith(".class") }
                .forEach { entry ->
                    jarFile.getInputStream(entry).use { inputStream ->
                        val classReader = ClassReader(inputStream)
                        val visitor = object : ClassVisitor(Opcodes.ASM9) {
                            override fun visit(version: Int, access: Int, name: String, signature: String?, superName: String?, interfaces: Array<String>?) {
                                if (superName != null) {
                                    hierarchyMap[name] = superName
                                    parentClasses.add(superName)
                                }
                                super.visit(version, access, name, signature, superName, interfaces)
                            }
                        }
                        classReader.accept(visitor, ClassReader.SKIP_DEBUG or ClassReader.SKIP_CODE or ClassReader.SKIP_FRAMES)
                    }
                }
        }
        return hierarchyMap.keys
            .filter { className ->
                val isSubclass = className.isSubclass(targetSuperclassName, hierarchyMap)
                val isLeaf = !parentClasses.contains(className)
                isSubclass && isLeaf
            }
    }

    private fun String.isSubclass(targetSuperclassName: String, hierarchyMap: Map<String, String>): Boolean {
        var currentClass: String? = this
        while (currentClass != null && hierarchyMap.containsKey(currentClass)) {
            val directSuperClass = hierarchyMap[currentClass]
            if (targetSuperclassName == directSuperClass) return true
            currentClass = directSuperClass
        }
        return false
    }

}