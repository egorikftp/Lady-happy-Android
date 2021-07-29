package com.egoriku.plugin.configure

import Libs
import com.egoriku.ext.implementation
import com.egoriku.ext.main
import com.egoriku.plugin.internal.libraryExtension
import com.egoriku.versions.ProjectVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidLibrary() = libraryExtension.run {
    plugins.apply("kotlin-android")

    defaultConfig {
        minSdk = ProjectVersion.minSdkVersion
        compileSdk = ProjectVersion.compileSdkVersion
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
        implementation(Libs.kotlin)
    }
}