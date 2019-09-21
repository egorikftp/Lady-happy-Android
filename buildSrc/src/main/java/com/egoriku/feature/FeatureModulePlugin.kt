package com.egoriku.feature

import com.egoriku.ext.allowExperimentalExtensions
import com.egoriku.library.LibraryModulePlugin
import org.gradle.api.Project

class FeatureModulePlugin : LibraryModulePlugin() {

    override fun apply(project: Project) {
        super.apply(project)
        with(project) {
            configurePlugins()
            allowExperimentalExtensions()
        }
    }
}

fun Project.configurePlugins() {
    with(plugins) {
        apply("kotlin-android-extensions")
    }
}