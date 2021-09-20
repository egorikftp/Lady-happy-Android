package com.egoriku.plugin.configure

import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.plugin.internal.applicationExtension
import com.egoriku.plugin.internal.libs
import org.gradle.api.Project

internal fun Project.configureAndroidApplication() = applicationExtension.run {
    plugins.apply("kotlin-android")

    defaultConfig {
        applicationId = "com.egoriku.ladyhappy"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
        versionCode = provideVersionCode()
        versionName = provideVersionName()
        resourceConfigurations + listOf("en", "ru")
    }

    buildTypes {
        release {
            isDebuggable = false
            multiDexEnabled = false
            isMinifyEnabled = true
            proguardFiles(
                "proguard-rules.pro",
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }

        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isMinifyEnabled = false
            multiDexEnabled = true
        }
    }
}
