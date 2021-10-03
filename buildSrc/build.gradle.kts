plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("de.fayard.refreshVersions:refreshVersions:0.10.1")

    // android gradle plugin, required by custom plugin


    implementation("com.android.tools.build:gradle:4.2.2")

    implementation(kotlin("gradle-plugin", "1.5.20"))

    implementation(gradleApi())
    implementation(localGroovy())
}