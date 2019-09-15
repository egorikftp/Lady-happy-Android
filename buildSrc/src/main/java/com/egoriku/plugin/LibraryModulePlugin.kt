package com.egoriku.plugin

import Libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class LibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            configurePlugins()
            configureAndroidSection()
            configureDependencies()
        }
    }
}

fun Project.configurePlugins() {
    with(plugins) {
        apply("com.android.library")
        apply("kotlin-android")
    }
}

fun Project.configureAndroidSection() = extensions.getByType<LibraryExtension>().run {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun Project.configureDependencies() = dependencies {
    add("implementation", Libs.kotlin)
}