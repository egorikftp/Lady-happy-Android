package com.egoriku.plugin.configure

import Libs
import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.ext.implementation
import com.egoriku.plugin.internal.applicationExtension
import com.egoriku.versions.ProjectVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidApplication() = applicationExtension.run {
    plugins.apply("kotlin-android")

    defaultConfig {
        applicationId = "com.egoriku.ladyhappy"
        compileSdk = ProjectVersion.compileSdkVersion
        minSdk = ProjectVersion.minSdkVersion
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

    dependencies {
        implementation(Libs.kotlin)
    }
}
