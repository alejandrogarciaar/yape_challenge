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
val googleMaps: String by extra

// Picasso
val picasso: String by extra

// Retrofit
val retrofit: String by extra

// Unit & Instrumentation testing
val junitVersion: String by extra
val androidJunit: String by extra
val espressoCore: String by extra
val kotlinCoroutines: String by extra
val androidCoreTesting: String by extra
val mockkVersion: String by extra

// Project config
val defaultCompileSdk: Int by extra
val defaultMinSdk: Int by extra

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.john.recipe_detail_impl"
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

    implementation(project(":libs:ui-components"))
    implementation(project(":libs:domain"))

    implementation("androidx.core:core-ktx:$androidxCore")
    implementation("androidx.appcompat:appcompat:$androidxAppCompat")
    implementation("com.google.android.material:material:$googleMaterial")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$androidxLiveData")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidxViewModel")
    implementation("androidx.fragment:fragment-ktx:$androidxFragment")

    implementation("com.squareup.picasso:picasso:$picasso")

    implementation("com.google.dagger:hilt-android:$daggerHilt")
    kapt("com.google.dagger:hilt-compiler:$daggerHilt")

    implementation("com.squareup.retrofit2:retrofit:$retrofit")

    implementation("com.google.code.gson:gson:$googleGson")

    implementation("com.google.android.gms:play-services-maps:$googleMaps")

    testImplementation("io.mockk:mockk-android:${mockkVersion}")
    testImplementation("io.mockk:mockk-agent:${mockkVersion}")
    testImplementation("io.mockk:mockk-agent-jvm:$mockkVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutines")
    testImplementation("androidx.arch.core:core-testing:$androidCoreTesting")
    testImplementation("junit:junit:$junitVersion")

    androidTestImplementation("androidx.test.ext:junit:$androidJunit")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCore")
}