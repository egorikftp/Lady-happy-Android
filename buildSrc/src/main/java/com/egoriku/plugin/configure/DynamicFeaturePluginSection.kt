package com.egoriku.plugin.configure

import Libs
import com.egoriku.ext.implementation
import com.egoriku.ext.main
import com.egoriku.plugin.internal.dynamicFeatureExtension
import com.egoriku.versions.ProjectVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidDynamicFeature() = dynamicFeatureExtension.run {
    plugins.apply("kotlin-android")

    defaultConfig {
        minSdk = ProjectVersion.minSdkVersion
        compileSdk = ProjectVersion.compileSdkVersion
        proguardFiles("proguard-rules-dynamic-features.pro")
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
