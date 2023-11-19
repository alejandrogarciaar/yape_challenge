apply(from = "../../global-dependencies.gradle.kts")
apply(from = "../../global-config.gradle.kts")

// AndroidX
val androidxCore: String by extra
val androidxAppCompat: String by extra

// Picasso
val picasso: String by extra

// Google
val googleMaterial: String by extra

// Project config
val defaultCompileSdk: Int by extra
val defaultMinSdk: Int by extra

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.john.ui_component"
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:$androidxCore")
    implementation("androidx.appcompat:appcompat:$androidxAppCompat")
    implementation("com.google.android.material:material:$googleMaterial")
    implementation("com.squareup.picasso:picasso:$picasso")
}