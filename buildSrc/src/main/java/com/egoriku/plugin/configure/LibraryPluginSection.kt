package com.egoriku.plugin.configure

import com.egoriku.ext.implementation
import com.egoriku.ext.main
import com.egoriku.plugin.internal.libraryExtension
import com.egoriku.plugin.internal.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidLibrary() = libraryExtension.run {
    plugins.apply("kotlin-android")

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }

        debug {
            isMinifyEnabled = false
        }
    }

    sourceSets {
        main {
            java.srcDirs("src/main/kotlin")
        }
    }

    dependencies {
        implementation(libs.kotlin)
    }
}