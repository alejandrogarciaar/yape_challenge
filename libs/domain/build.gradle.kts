apply(from = "../../global-dependencies.gradle.kts")
apply(from = "../../global-config.gradle.kts")

// Dependencies
val androidxCore: String by extra

// Config
val defaultCompileSdk: Int by extra
val defaultMinSdk: Int by extra

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.john.domain"
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
    implementation("androidx.core:core-ktx:$androidxCore")
}