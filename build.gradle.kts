plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.ckr.blocgenerator"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

// Configure for both IntelliJ IDEA and Android Studio compatibility
intellij {
    version.set("2023.2.8") // Base IntelliJ version
    type.set("IC") // IntelliJ IDEA Community Edition

    plugins.set(listOf(
        "com.intellij.java",
        "org.jetbrains.android" // Android plugin support
    ))
}

// Use Java 17 for compatibility
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    patchPluginXml {
        sinceBuild.set("232") // Support IntelliJ 2023.2+
        untilBuild.set("252.*") // Support Android Studio and future versions
    }

    buildSearchableOptions {
        enabled = false
    }

    verifyPlugin {
        enabled = false
    }

    // Create separate distributions for different IDEs
    register("buildForAndroidStudio") {
        dependsOn("buildPlugin")
        doLast {
            println("✅ Plugin built for Android Studio compatibility!")
            println("📁 Install from: build/distributions/flutter-bloc-generator-1.0.0.zip")
        }
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}