import java.util.Properties
import kotlin.apply

plugins {
    `kotlin-dsl`
    alias(libs.plugins.gradle.publish)
}

group = "cc.mdvsc"
version = "1.0.0"

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:-unchecked")
}

val embeddedJars: Configuration by configurations.creating
configurations.compileOnly.get().extendsFrom(embeddedJars)

dependencies {
    implementation(libs.asm)
    implementation(libs.asm.commons)
    implementation(libs.proguard.gradle)
    implementation(libs.kotlin.gradle.plugin)
    embeddedJars(files("libs/rapc.jar"))
    embeddedJars(files("libs/jadtool.jar"))
    compileOnly(files("libs/net_rim_api.jar"))
}


val pluginName = "rapcBuilder"

gradlePlugin {
    website = "https://github.com/HanikLZ/bb-rpac"
    vcsUrl = "https://github.com/HanikLZ/bb-rpac"
    plugins {
        create(pluginName) {
            id = "${project.group}.${project.name}"
            isAutomatedPublishing = true
            displayName = "RapcBuilder Plugin"
            description = "A Gradle plugin that brings modern Kotlin development to classic BlackBerry and J2ME devices."
            tags = listOf("kotlin", "java", "javame", "j2me", "blackberry")
            implementationClass = "org.mdvsc.rapc.RapcPlugin"
        }
    }
}

tasks.jar {
    from(embeddedJars.map(::zipTree))
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.withType<Jar>().configureEach {
    if (name == "sourcesJar") include("**/*.java", "**/*.kt")
}

val mavenUrl: String by project
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) localPropertiesFile.reader().use { load(it) }
}

publishing {
    repositories {
        maven {
            credentials {
                url = uri(mavenUrl)
                username = localProperties.getProperty("publish.user")
                password = localProperties.getProperty("publish.password")
            }
        }
    }
}