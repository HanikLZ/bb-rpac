package org.mdvsc.rapc

import com.sun.midp.jadtool.JadTool
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import java.io.FileOutputStream
import java.util.jar.Manifest

abstract class RapcJadSignTask: DefaultTask() {

    @get:InputFile
    abstract val jadFile: RegularFileProperty

    @get:Input
    @get:Optional
    abstract val jarUrl: Property<String>

    @get:InputDirectory
    abstract val destPath: DirectoryProperty

    @get:InputFile
    abstract val jarFile: RegularFileProperty

    @get:OutputFile
    abstract val outputJadFile: RegularFileProperty

    @get:InputFile
    abstract val keystore: RegularFileProperty

    init {
        destPath.convention(project.layout.dir(jarFile.map { it.asFile.parentFile }))
        outputJadFile.convention(destPath.zip(jadFile) { dir, app ->
            dir.asFile.apply { if (!exists()) mkdirs() }
            dir.file("${app.asFile.nameWithoutExtension}.jad")
        })
    }

    @TaskAction
    fun generate() {
        val jar = jarFile.asFile.get()
        val jad = outputJadFile.asFile.get()
        val mf = jadFile.asFile.get().inputStream().use(::Manifest)
        val attrs = mf.mainAttributes
        attrs.putValue("MIDlet-Jar-Size", jar.length().toString())
        attrs.putValue("MIDlet-Jar-URL", jarUrl.orNull?.run { if (endsWith(".jar")) this else "$this/${jar.name}" } ?:jar.name)
        FileOutputStream(jad)
            .bufferedWriter()
            .use { attrs.entries.forEach { (k, v) -> if (!it.toString().startsWith("RIM-")) it.appendLine("$k: $v") } }
        keystore.orNull?.let { keystore ->
            val params = arrayOf(
                "-alias",
                "verisign",
                "-storepass",
                "password",
                "-keystore",
                keystore.toString(),
                "-inputjad",
                jad.absolutePath,
                "-outputjad",
                jad.absolutePath)
            JadTool.main(arrayOf("-addcert") + params)
            JadTool.main(arrayOf("-addjarsig", "-jarfile", jar.absolutePath, "-keypass", "password") + params)
        }
    }
}
