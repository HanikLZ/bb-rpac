package org.mdvsc.rapc

import net.rim.tools.compiler.Compiler
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

abstract class RapcTask: DefaultTask() {

    @get:Input
    abstract val nopreverified: Property<Boolean>

    @get:Input
    abstract val quiet: Property<Boolean>

    @get:Input
    abstract val verbose: Property<Boolean>

    @get:Input
    abstract val nolimit: Property<Boolean>

    @get:Input
    abstract val convertpng: Property<Boolean>

    @get:Input
    abstract val encoding: Property<String>

    @get:Input
    abstract val midlet: Property<Boolean>

    @get:Input
    abstract val library: Property<Boolean>

    @get:InputFile
    abstract val rapcFile: RegularFileProperty

    @get:InputFile
    abstract val rimApiLibFile: RegularFileProperty

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val sourceFiles: ConfigurableFileCollection

    @get:InputDirectory
    abstract val destPath: DirectoryProperty

    @get:OutputFile
    abstract val codFile: RegularFileProperty

    init {
        destPath.convention(project.layout.dir(rapcFile.map { it.asFile.parentFile }))
        codFile.convention(destPath.zip(rapcFile) { dir, app ->
            dir.asFile.apply { if (!exists()) mkdirs() }
            dir.file(app.asFile.nameWithoutExtension)
        })
        nopreverified.convention(false)
        verbose.convention(true)
        nolimit.convention(true)
        quiet.convention(true)
        convertpng.convention(false)
        midlet.convention(false)
        library.convention(false)
        encoding.convention("UTF_8")
    }

    @TaskAction
    fun execute() {
        val finalCodFile = codFile.get().asFile
        finalCodFile.parentFile.mkdirs()
        Compiler.compile(listOfNotNull(
            if (nopreverified.get()) "-nopreverified" else null,
            if (verbose.get()) "-verbose" else null,
            if (nolimit.get()) "-nolimit" else null,
            if (quiet.get()) "-quiet" else null,
            if (convertpng.get()) "-convertpng" else null,
            "-debugclass",
            "-nojar",
            encoding.orNull?.let { "-Dfile.encoding=$it" },
            if (midlet.get()) "-midlet" else "-target=java6",
            "import=${rimApiLibFile.get().asFile.absolutePath}",
            "${if (library.get()) "library" else "codename"}=${finalCodFile.absolutePath}",
            rapcFile.get().asFile.absolutePath,
            sourceFiles.singleFile.absolutePath
        ).toTypedArray())
    }

}
