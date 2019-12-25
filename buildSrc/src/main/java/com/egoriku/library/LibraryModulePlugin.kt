package com.egoriku.library

import com.android.build.gradle.LibraryExtension
import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.versions.ProjectVersion
import com.egoriku.ext.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

open class LibraryModulePlugin : Plugin<Project> {

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
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = 1
        versionName = "1.0"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    viewBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun Project.configureDependencies() = dependencies {
    implementation(Libs.kotlin)
}