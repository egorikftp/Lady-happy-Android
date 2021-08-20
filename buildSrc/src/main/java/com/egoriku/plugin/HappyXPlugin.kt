package com.egoriku.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.plugins.DynamicFeaturePlugin
import com.egoriku.plugin.configure.configureAndroidApplication
import com.egoriku.plugin.configure.configureAndroidDynamicFeature
import com.egoriku.plugin.configure.configureAndroidLibrary
import com.egoriku.plugin.internal.applicationExtension
import com.egoriku.plugin.internal.dynamicFeatureExtension
import com.egoriku.plugin.internal.libraryExtension
import com.egoriku.plugin.internal.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.create

open class HappyXPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.all {
                when (this) {
                    is JavaPlugin -> println("This is JavaPlugin")
                    is LibraryPlugin -> configureAndroidLibrary()
                    is AppPlugin -> configureAndroidApplication()
                    is DynamicFeaturePlugin -> configureAndroidDynamicFeature()
                }
            }
        }

        val config = project.extensions.create<HappyXPluginExtension>("happyPlugin")

        project.afterEvaluate {
            plugins.all {
                when (this) {
                    is LibraryPlugin -> {
                        libraryExtension.run {
                            buildFeatures.viewBinding = config.viewBindingEnabled

                            if (config.composeEnabled) {
                                buildFeatures.compose = config.composeEnabled

                                composeOptions {
                                    kotlinCompilerExtensionVersion = libs.versions.compose.get()
                                }
                            }
                        }

                        enableParcelize(config)
                    }

                    is AppPlugin -> {
                        applicationExtension.run {
                            buildFeatures.viewBinding = config.viewBindingEnabled

                            if (config.composeEnabled) {
                                buildFeatures.compose = config.composeEnabled

                                composeOptions {
                                    kotlinCompilerExtensionVersion = libs.versions.compose.get()
                                }
                            }
                        }
                    }

                    is DynamicFeaturePlugin -> {
                        enableParcelize(config)

                        dynamicFeatureExtension.run {
                            buildFeatures.viewBinding = config.viewBindingEnabled

                            if (config.composeEnabled) {
                                buildFeatures.compose = config.composeEnabled

                                composeOptions {
                                    kotlinCompilerExtensionVersion = libs.versions.compose.get()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun Project.enableParcelize(config: HappyXPluginExtension) {
    if (config.kotlinParcelize) {
        plugins.apply("kotlin-parcelize")
    }
}