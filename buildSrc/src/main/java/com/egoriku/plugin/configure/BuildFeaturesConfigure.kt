package com.egoriku.plugin.configure

import com.android.build.api.dsl.CommonExtension
import com.egoriku.plugin.HappyXPluginExtension
import com.egoriku.plugin.internal.libs
import org.gradle.api.Project

fun CommonExtension<*, *, *, *>.configureBuildFeatures(
    project: Project,
    pluginExtension: HappyXPluginExtension
) {
    buildFeatures {
        viewBinding = pluginExtension.viewBinding
        buildConfig = pluginExtension.buildConfigGeneration

        if (pluginExtension.compose) {
            compose = pluginExtension.compose

            composeOptions {
                kotlinCompilerExtensionVersion = project.libs.versions.compose.get()
            }
        }
    }

    if (pluginExtension.kotlinParcelize) {
        project.plugins.apply("kotlin-parcelize")
    }
}