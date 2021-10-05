plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")

    id("androidx.navigation.safeargs.kotlin")

    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
}

android {

    configCommon()

    defaultConfig {
        applicationId = "com.zxdmjr.filestorageaccessdemo"
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

    buildFeatures {
        viewBinding = true
    }
}

setupCommonDependencies()
dependencies {
    implementation(project(mapOf("path" to ":storagektx")))

    useNavigation()
//    useRoom()
    useHilt()

    implementation(Google.Android.material)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.constraintLayout)

    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.lifecycle.liveDataKtx)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.commonJava8)

    implementation(AndroidX.fragmentKtx)

    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.converter.gson)
    implementation(Square.okio)

}

