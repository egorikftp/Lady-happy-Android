package com.egoriku.plugin.configure

import com.egoriku.plugin.internal.dynamicFeatureExtension
import com.egoriku.plugin.internal.libs
import org.gradle.api.Project

internal fun Project.configureAndroidDynamicFeature() = dynamicFeatureExtension.run {
    plugins.apply("kotlin-android")

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
        proguardFiles("proguard-rules-dynamic-features.pro")
    }
}