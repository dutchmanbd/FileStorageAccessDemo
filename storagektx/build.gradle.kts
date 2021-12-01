plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}

// NOTE : Delete afterEvaluate code if you publish Native Java / Kotlin Library
// NOTE : Just Using publishing function
afterEvaluate {
    publishing {
        publications {

            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {

                // Applies the component for the release build variant.
                // NOTE : Delete this line code if you publish Native Java / Kotlin Library
                from(components["release"])

                // Library Package Name (Example : "com.frogobox.androidfirstlib")
                // NOTE : Different GroupId For Each Library / Module, So That Each Library Is Not Overwritten
                groupId = "com.zxdmjr.storeagektx"

                // Library Name / Module Name (Example : "androidfirstlib")
                // NOTE : Different ArtifactId For Each Library / Module, So That Each Library Is Not Overwritten
                artifactId = "storagektx"

                // Version Library Name (Example : "1.0.0")
                version = "1.0.0"

            }

        }
    }
}

android {
    configCommon()

    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

setupCommonDependencies()

dependencies {

    implementation(Google.Android.material)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.constraintLayout)
}