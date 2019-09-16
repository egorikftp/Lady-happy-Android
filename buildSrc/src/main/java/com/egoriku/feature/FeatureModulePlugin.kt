package com.egoriku.feature

import com.egoriku.getExtensionByName
import com.egoriku.library.LibraryModulePlugin
import com.egoriku.toExtensionAware
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

class FeatureModulePlugin : LibraryModulePlugin() {

    override fun apply(project: Project) {
        super.apply(project)
        with(project) {
            configurePlugins()
            configureAndroidExtensions()
        }
    }
}

fun Project.configurePlugins() {
    with(plugins) {
        apply("kotlin-android-extensions")
    }
}

fun Project.configureAndroidExtensions() {
    toExtensionAware()
            .getExtensionByName<AndroidExtensionsExtension>("androidExtensions")
            .run {
                isExperimental = true
                defaultCacheImplementation = CacheImplementation.SPARSE_ARRAY
            }
}