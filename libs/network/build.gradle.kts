apply(from = "../../global-dependencies.gradle.kts")
apply(from = "../../global-config.gradle.kts")

// Depedencies
val retrofit: String by extra

// Project config
val defaultCompileSdk: Int by extra
val defaultMinSdk: Int by extra

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.john.network"
    compileSdk = defaultCompileSdk

    defaultConfig {
        minSdk = defaultMinSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api("com.squareup.retrofit2:retrofit:$retrofit")
    api("com.squareup.retrofit2:converter-gson:$retrofit")
}