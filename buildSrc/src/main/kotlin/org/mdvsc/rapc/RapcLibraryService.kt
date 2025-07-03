package org.mdvsc.rapc

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

abstract class RapcLibraryService: BuildService<RapcLibraryService.JarExtractorServiceParams>, AutoCloseable {

    companion object {
        const val SDK_NAME = "net_rim_api"
        private const val SDK_JAR = "$SDK_NAME.jar"
        private const val SDK_DOC_JAR = "$SDK_NAME-javadoc.jar"
        private const val KEYSTORE = "android.keystore"
    }

    interface JarExtractorServiceParams : BuildServiceParameters {
        val gradleUserHome: DirectoryProperty
    }

    private val lock = Any() // 用于线程同步
    val sdkJarFile: File
    val sdkJarDocFile: File
    val keyStoreFile: File

    init {
        val cacheBaseDir = parameters.gradleUserHome.get().asFile.resolve("caches/${this::class.java.packageName}")
        val sdkPath = cacheBaseDir.resolve("sdk")
        sdkPath.mkdirs()
        val parentPath = sdkPath.absolutePath
        synchronized(lock) {
            keyStoreFile = extractResource(KEYSTORE, parentPath)
            sdkJarFile = extractResource(SDK_JAR, parentPath)
            sdkJarDocFile = extractResource(SDK_DOC_JAR, parentPath)
        }
    }

    private fun extractResource(resource: String, targetPath: String): File {
        val target = Paths.get(targetPath, resource)
        if (!Files.exists(target)) javaClass.getResourceAsStream("/$resource")?.run {
            val tempPath = Paths.get(targetPath, "$resource.tmp")
            use { stream ->
                Files.copy(stream, tempPath, StandardCopyOption.REPLACE_EXISTING)
                Files.move(tempPath, target, StandardCopyOption.ATOMIC_MOVE)
            }
        } ?: throw IllegalStateException("Cannot find resource: $resource")
        return target.toFile()
    }

    override fun close() = Unit

}