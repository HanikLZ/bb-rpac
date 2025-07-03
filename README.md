# RAPC Gradle Plugin

<!-- Badges -->
![Build Status](https://img.shields.io/github/actions/workflow/status/[your-github-username]/[your-repo-name]/gradle.yml?branch=main)
[![Gradle Plugin Portal](https://img.shields.io/badge/Plugin-Not_Published-yellow)](https://plugins.gradle.org/)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This plugin aims to **breathe new life into legacy hardware** by introducing modern development workflows to classic devices. It brings the modern, efficient **Kotlin** language to the classic **BlackBerry Java OS** and **J2ME (Java Platform, Micro Edition)** platforms.

Now, you can build applications for these classic platforms on any operating system (Windows, macOS, or Linux) with a single Gradle command.

### Core Features

*   **Truly Cross-Platform**: Build your projects on Windows, macOS, and Linux.
*   **Dual-Platform Workflow**:
    *   **BlackBerry Platform**: Use the `rapc` task to build native apps, MIDlets, and libraries for BlackBerry OS, outputting `.cod` files.
    *   **Generic J2ME**: Use the `midlet` task to build standard `.jar` and `.jad` files for J2ME-enabled devices.
*   **Kotlin on Classic Platforms**: Write your application code in modern, concise, and safe Kotlin.
*   **Compatibility & Enhancements**: Includes built-in compatibility patches and enables the use of default methods in Kotlin interfaces.
*   **One-Click Packaging & Signing**: Easily generate platform-specific, deployable files.
*   **Deployment Helper (Optional)**: Provides a `deploy` task for BlackBerry to simplify the installation process.

## Important Development Notes

Before you start, it is crucial to understand the limitations of the BlackBerry OS development environment.

### 1. It's Not Standard Java
The BlackBerry OS runs on **Java ME CLDC**, not a standard JDK.
*   **API Incompatibility**: Most standard Java SE APIs are **not available**. You must use the proprietary APIs provided by the BlackBerry SDK.
*   **Language Features**: Features like `default` methods in Java interfaces are **not allowed**.

### 2. Kotlin Language Support
*   **Enums Are Not Supported**: The `enum` keyword in Kotlin is **not compatible**. Use constants or sealed classes as alternatives.
*   **Interface Default Methods**: This plugin **enables support for Kotlin interface default methods** by processing the bytecode.
*   **Standard Library**: Many parts of the Kotlin standard library will not function if they rely on unavailable JDK classes.

### 3. Bytecode Compatibility
*   The target bytecode version is **capped at Java 11**.
*   The plugin will **automatically downgrade** a higher `jvmTarget` to 11.

### 4. Code Signing Is Not Possible
*   **Reason**: BlackBerry **shut down its official signing services** years ago. This is a platform-wide limitation.
*   **Consequence**: Your application will run as an "unsigned" app and cannot access APIs that require signatures.

---

## Quick Start

### 1. Project Setup

Ensure your project has the Kotlin JVM plugin applied. This plugin is designed to work alongside it.

**`build.gradle.kts`**
```kotlin
plugins {
    kotlin("jvm") version "2.2.0" // Use a compatible Kotlin version
}
```

### 2. Project Setup

Apply the RapcPlugin to your build script.

**`build.gradle.kts`**
```kotlin
apply<RapcPlugin>()
```
### 3. Configure Your Application

Configure your application's metadata using the RapcExtension block. This information will be embedded in the final .cod file.

**`build.gradle.kts`**
```kotlin
//...
configure<RapcExtension> {
    // javaLoader.set(file("path")) // javaloader.exe path, optional
    // codOutputPath.set(file("build/dists")) // blackberry cod output path, default as build/dists
    // midletOutputPath.set(file("build/dists")) // midlet jar output path, default as build/dists
    app {
        name = "org_mdvsc_rapc_sample" // The output COD or Jar(midlet) file name (without extension)
        version = "1.0.0"
        vendor = "MDVSC"
        description = "A sample app built with Kotlin and RAPC Gradle Plugin."
        // midlet = false // or true for compile Blackberry app as midlet mod
        // library = false // or true for compile Blackberry library
        // jarUrl = "abc.jar" midlet jar url field. default as name.jar

        entry {
            title = "MyApp" // Title shown on the BlackBerry home screen
            // argumensts = "" // entry arguments.
            icon {
                normal = "icon.png"
                // focus = "icon_hover.png" // Optional: icon on focus，if target midlet, this field not use.
            }
        }

        // this["morekey"] = "value" // extra key value

    }
}
```

### 4. Build the Application

Run the rapc task to compile your Kotlin/Java source code and package it into a .cod file.

```
./gradlew rapc
```

Or targeting Generic J2ME jar compile, use task
```
./gradlew midlet
```
If need jad signed for j2me app, you can use task
```
./gradlew midlet-sign
```
This task generate jad for jar, and auto sign it by versign cer (expired).

After the build succeeds, you will find the final installable file in the build/dists/ directory (e.g., build/dists/org_mdvsc_rapc_sample.cod, or build/dists/org_mdvsc_rapc_sample-midlet.jar).

### 5. Deploy to Device

#### Method 1: Manual Installation (All Platforms)
This is the universal method. Simply copy the generated .cod file (and any sibling .cod files if they exist) from the build/rapc/ directory to your BlackBerry device's SD card and install it from there.

#### Method 2: Automated Deployment (Optional, Windows Only)
For developers on Windows, the plugin can automate installation using javaloader.exe. This tool is part of the BlackBerry JDE/SDK and is not included with the plugin.

**`build.gradle.kts`**
```kotlin
configure<RapcExtension> {
    //... (app configuration)

    // Set the path to your JavaLoader executable
    javaLoader.set("C:/BlackBerryJDE/bin/javaloader.exe")
}
```
Then, run the deploy task. Make sure your device is connected via USB and recognized by your computer.
```
./gradlew deploy
```
---

## Roadmap

This project is actively evolving. We welcome contributions to help us reach our goals.

*   ✅ **Current Support:** BlackBerry Java OS / J2ME 
*   ▶️ **Next Step:** Bringing modern Kotlin development to a wider range of classic mobile devices.

## License
This project is licensed under the Apache License 2.0. See the LICENSE file for details.

## Contributing
Contributions are welcome! Please feel free to open an issue or submit a pull request.