apply(from = "../../../global-dependencies.gradle.kts")
apply(from = "../../../global-config.gradle.kts")

// AndroidX
val androidxCore: String by extra
val androidxAppCompat: String by extra
val androidxLiveData: String by extra
val androidxViewModel: String by extra
val androidxFragment: String by extra

// Dagger
val daggerHilt: String by extra

// Google
val googleMaterial: String by extra
val googleGson: String by extra

// Retrofit
val retrofit: String by extra

// Unit & Instrumentation testing
val junitVersion: String by extra
val androidJunit: String by extra
val espressoCore: String by extra
val mockitoKotlin: String by extra
val mockitoCore: String by extra
val mockitoInline: String by extra
val kotlinCoroutines: String by extra
val androidCoreTesting: String by extra

// Project config
val defaultCompileSdk: Int by extra
val defaultMinSdk: Int by extra

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.john.home_wiring_impl"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    api(project(":features:home:home-api"))
    api(project(":features:home:home-impl"))

    implementation("com.google.dagger:hilt-android:$daggerHilt")
    kapt("com.google.dagger:hilt-compiler:$daggerHilt")
}