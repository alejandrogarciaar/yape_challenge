apply(from = "../global-dependencies.gradle.kts")
apply(from = "../global-config.gradle.kts")

// Dependencies
val androidxCore: String by extra
val androidxAppCompat: String by extra

// Material
val googleMaterial: String by extra

// Retrofit
val retrofit: String by extra

// Dagger
val daggerHilt: String by extra

// Unit testing
val junitVersion: String by extra
val androidJunit: String by extra
val espressoCore: String by extra

// Config
val defaultCompileSdk: Int by extra
val defaultMinSdk: Int by extra
val defaultTargetSdk: Int by extra
val yapeVersionCode: Int by extra
val yapeVersionName: String by extra

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.john.yapeapp"
    compileSdk = defaultCompileSdk

    defaultConfig {
        applicationId = "com.john.yapeapp"
        minSdk = defaultMinSdk
        targetSdk = defaultTargetSdk
        versionCode = yapeVersionCode
        versionName = yapeVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(project(":features:home:home-wiring-impl"))
    implementation(project(":features:recipe-detail:recipe-detail-wiring-impl"))

    implementation(project(":libs:ui-components"))

    implementation("androidx.core:core-ktx:$androidxCore")
    implementation("androidx.appcompat:appcompat:$androidxAppCompat")
    implementation("com.google.android.material:material:$googleMaterial")

    implementation("com.google.dagger:hilt-android:$daggerHilt")
    kapt("com.google.dagger:hilt-compiler:$daggerHilt")

    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")

    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidJunit")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoCore")

}