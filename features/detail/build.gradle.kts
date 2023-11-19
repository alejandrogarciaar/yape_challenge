apply(from = "../../global-dependencies.gradle.kts")
apply(from = "../../global-config.gradle.kts")

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

// Picasso
val picasso: String by extra

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
    id("kotlin-parcelize")
}

android {
    namespace = "com.john.detail"
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

    implementation(project(":libs:domain"))
    implementation(project(":libs:navigation"))
    implementation(project(":libs:ui-components"))

    implementation("androidx.core:core-ktx:$androidxCore")
    implementation("androidx.appcompat:appcompat:$androidxAppCompat")
    implementation("com.google.android.material:material:$googleMaterial")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$androidxLiveData")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidxViewModel")
    implementation("androidx.fragment:fragment-ktx:$androidxFragment")

    implementation("com.squareup.picasso:picasso:$picasso")

    implementation("com.google.dagger:hilt-android:$daggerHilt")
    kapt("com.google.dagger:hilt-compiler:$daggerHilt")

    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlin")
    testImplementation("org.mockito:mockito-core:$mockitoCore")
    testImplementation("org.mockito:mockito-inline:$mockitoInline")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutines")
    testImplementation("androidx.arch.core:core-testing:$androidCoreTesting")
    testImplementation("junit:junit:$junitVersion")

    androidTestImplementation("androidx.test.ext:junit:$androidJunit")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCore")
}