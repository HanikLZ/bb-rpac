dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            val kotlinVersion = "2.2.0"
            plugin("kotlin-serialization", "org.jetbrains.kotlin.plugin.serialization").version(kotlinVersion)
            plugin("gradle-publish", "com.gradle.plugin-publish").version("1.3.1")
            library("kotlin-gradle-plugin", "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
            library("kotlinx-serialization-json", "org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
            library("proguard-gradle", "com.guardsquare:proguard-gradle:7.7.0")
            val asmVersion = "9.8"
            library("asm", "org.ow2.asm:asm:$asmVersion")
            library("asm-commons", "org.ow2.asm:asm-commons:$asmVersion")
        }
    }
}

rootProject.name = "rapc"