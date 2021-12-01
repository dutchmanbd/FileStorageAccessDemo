buildscript {
    val kotlin_version by extra("1.5.31")
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }

//        // Localazy Repository
//        maven("https://maven.localazy.com/repository/release/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath(AndroidX.navigation.safeArgsGradlePlugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
