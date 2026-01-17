// build.gradle.kts (root) 

plugins {
    // Apply version catalog access
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

// Add this block at the bottom
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Keep these for backward compatibility
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
    }
}