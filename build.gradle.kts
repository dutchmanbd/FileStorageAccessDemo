buildscript {
    val kotlin_version by extra("1.5.20")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        // Localazy Repository
        maven("https://maven.localazy.com/repository/release/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
        classpath(AndroidX.navigation.safeArgsGradlePlugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()

        maven("https://jitpack.io")
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
