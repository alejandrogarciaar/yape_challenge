buildscript {
    apply(from = "global-dependencies.gradle.kts")
    val daggerHilt: String by extra
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:$daggerHilt")
    }
}

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.android.library") version "8.1.3" apply false
    kotlin("kapt") version "1.9.20"
}