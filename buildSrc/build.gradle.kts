plugins {
    `kotlin-dsl`
    alias(libs.plugins.gradle.publish)
}

group = "org.mdvsc"
version = "1.0.0"

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:-unchecked")
}

dependencies {
    implementation(libs.asm)
    implementation(libs.asm.commons)
    implementation(libs.proguard.gradle)
    implementation(libs.kotlin.gradle.plugin)

    implementation(files("libs/rapc.jar"))
    implementation(files("libs/jadtool.jar"))
    compileOnly(files("libs/net_rim_api.jar"))
}

gradlePlugin {
    plugins {
        register("") {
            id = "${project.group}.${project.name}"
            implementationClass = "$id.RapcPlugin"
        }
    }
}
/*

pluginBundle {
    website = 'https://github.com/your-username/my-awesome-plugin' // 你的插件网站地址
    vcsUrl = 'https://github.com/your-username/my-awesome-plugin.git' // 你的版本控制系统地址
    description = 'A brief description of my awesome plugin.' // 插件描述
    tags = ['example', 'awesome', 'plugin'] // 标签，方便搜索

    plugins {
        myAwesomePlugin {
            displayName = 'My Awesome Gradle Plugin' // 在门户网站上显示的插件名称
        }
    }
}*/
