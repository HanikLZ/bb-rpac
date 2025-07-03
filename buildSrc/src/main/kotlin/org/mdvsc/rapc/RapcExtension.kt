package org.mdvsc.rapc

import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.mdvsc.rapc.data.App
import javax.inject.Inject

abstract class RapcExtension @Inject constructor(project: Project) {

    abstract val codOutputPath: DirectoryProperty
    abstract val midletOutputPath: DirectoryProperty
    abstract val javaLoader: RegularFileProperty
    var quiet: Boolean = false
    val app = App()

    init {
        javaLoader.convention(project.layout.projectDirectory.file("javaloader"))
        codOutputPath.convention(project.layout.buildDirectory.dir("cod"))
        midletOutputPath.convention(project.layout.buildDirectory.dir("midlet"))
        app.version = project.version.toString()
        app.name = project.name
    }
}
