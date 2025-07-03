package org.mdvsc.rapc

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.mdvsc.rapc.transform.RIMClassRewriter
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.ClassRemapper
import org.objectweb.asm.commons.SimpleRemapper
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipException

abstract class RapcPatchTask: DefaultTask() {

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val sourceFiles: ConfigurableFileCollection

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Input
    abstract val computeFrames: Property<Boolean>

    @get:Input
    abstract val dontWriteKt: Property<Boolean>

    private val remapper = SimpleRemapper(mapOf("java/util/Arrays" to RIMClassRewriter.REWRITE_CLASS_ARRAYS,
        "java/util/Collection" to RIMClassRewriter.REWRITE_CLASS_COLLECTION,
        "java/util/List" to RIMClassRewriter.REWRITE_CLASS_LIST,
        "java/util/AbstractCollection" to RIMClassRewriter.REWRITE_CLASS_ABSTRACT_COLLECTION,
        "java/util/AbstractList" to RIMClassRewriter.REWRITE_CLASS_ABSTRACT_LIST,
        "java/util/ConcurrentModificationException" to RIMClassRewriter.REWRITE_CLASS_CONCURRENT_MODIFICATION_EXCEPTION,
        "java/util/IndexOutOfBoundsException" to RIMClassRewriter.REWRITE_CLASS_INDEX_OUT_OF_BOUNDS_EXCEPTION,
        "java/util/RandomAccess" to RIMClassRewriter.REWRITE_CLASS_RANDOM_ACCESS,
        "java/util/ArrayList" to RIMClassRewriter.REWRITE_CLASS_ARRAY_LIST,
        "java/lang/Iterable" to RIMClassRewriter.REWRITE_CLASS_ITERABLE,
        "java/util/Iterator" to RIMClassRewriter.REWRITE_CLASS_ITERATOR,
        "java/util/Collections" to RIMClassRewriter.REWRITE_CLASS_COLLECTIONS,
        "java.util.Comparator" to RIMClassRewriter.REWRITE_CLASS_COMPARATOR,
        "java/lang/CharSequence" to "java/lang/String",
        "java.lang.StringBuilder" to "java.lang.StringBuffer",
    ))

    init {
        outputFile.convention(sourceFiles.elements.map { files ->
            val inputFile = files.first().asFile
            val parentDir = inputFile.parentFile
            val fileName = "${inputFile.nameWithoutExtension}-patched.${inputFile.extension}"
            project.layout.projectDirectory.dir(parentDir.absolutePath).file(fileName)
        })
        computeFrames.convention(false)
        dontWriteKt.convention(false)
    }


    @TaskAction
    fun execute() {
        val inputJar = sourceFiles.singleFile
        val outputJar = outputFile.get().asFile
        val writeFlags = if (computeFrames.get()) ClassWriter.COMPUTE_FRAMES else ClassWriter.COMPUTE_MAXS

        JarOutputStream(outputJar.outputStream().buffered()).use { jos ->
            val jarFile = JarFile(inputJar)
            var detectedClassVersion: Int? = null
            for (entry in jarFile.entries()) {
                jos.putNextEntry(ZipEntry(entry.name))
                jarFile.getInputStream(entry).use { jis ->
                    if (entry.name.endsWith(".class")) {
                        val classReader = ClassReader(jis)
                        if (detectedClassVersion == null) detectedClassVersion = classReader.readInt(4)
                        val classWriter = ClassWriter(classReader, writeFlags)
                        val rewriter = RIMClassRewriter(ClassRemapper(classWriter, remapper))
                        classReader.accept(rewriter, ClassReader.EXPAND_FRAMES)
                        jos.write(classWriter.toByteArray())
                    } else jis.copyTo(jos)
                }
                jos.closeEntry()
            }
            if (!dontWriteKt.get()) {
                jos.writeClass(RIMClassRewriter.REWRITE_CLASS_STRING_KT, writeFlags, detectedClassVersion)
                jos.writeClass(RIMClassRewriter.REWRITE_CLASS_COLLECTIONS_KT, writeFlags, detectedClassVersion)
                jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ARRAYS_KT, writeFlags, detectedClassVersion)
            }
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ARRAYS, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_COLLECTIONS, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_COLLECTION_ENUMERATION, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_SINGLETON_LIST, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ITERATOR, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ITERABLE, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_LIST_ITERATOR, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_COLLECTION, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_LIST, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ABSTRACT_COLLECTION, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ABSTRACT_LIST, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_LIST_ITR, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ITR, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_SUB_LIST, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_SUB_ARRAY_LIST, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_CONCURRENT_MODIFICATION_EXCEPTION, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_INDEX_OUT_OF_BOUNDS_EXCEPTION, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ARRAY_LIST, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_ARRAY_LIST_ITR, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_RANDOM_ACCESS, writeFlags, detectedClassVersion)
            jos.writeClass(RIMClassRewriter.REWRITE_CLASS_COMPARATOR, writeFlags, detectedClassVersion)
        }
    }

    private fun JarOutputStream.writeClass(clz: String, writeFlags: Int, classVersion: Int?) {
        val className = "$clz.class"
        RapcPatchTask::class.java.classLoader.getResourceAsStream(className)?.use {
            try {
                putNextEntry(JarEntry(className))
                if (classVersion != null) {
                    val reader = ClassReader(it)
                    val writer = ClassWriter(reader, writeFlags)
                    val versionUnifyVisitor = object : ClassVisitor(Opcodes.ASM9, writer) {
                        override fun visit(
                            version: Int,
                            access: Int,
                            name: String?,
                            signature: String?,
                            superName: String?,
                            interfaces: Array<String>?
                        ) = super.visit(classVersion, access, name, signature, superName, interfaces)
                    }
                    reader.accept(versionUnifyVisitor, 0)
                    write(writer.toByteArray())
                } else it.copyTo(this)
                closeEntry()
            } catch (_: ZipException) { }
        } ?: throw GradleException("Could not find adapter class '$className' in buildSrc classpath. Please ensure it is in buildSrc/src/main/...")
    }

}