package com.egoriku.plugin

import Libs
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.plugins.DynamicFeaturePlugin
import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.ext.debug
import com.egoriku.ext.implementation
import com.egoriku.ext.release
import com.egoriku.plugin.extension.MyPluginExtension
import com.egoriku.plugin.internal.androidExtensions
import com.egoriku.plugin.internal.dynamicFeatureExtension
import com.egoriku.plugin.internal.libraryExtension
import com.egoriku.versions.ProjectVersion
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsFeature
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

open class HappyXPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.all {
                when (this) {
                    is JavaPlugin -> println("This is JavaPlugin")
                    is LibraryPlugin -> {
                        addCommonPlugins()
                        addAndroidLibrarySection()
                        addCommonDependencies()
                    }
                    is AppPlugin -> {
                        addCommonPlugins()
                        addCommonDependencies()
                    }
                    is DynamicFeaturePlugin -> {
                        println("This is DynamicFeaturePlugin")
                        addCommonPlugins()
                        addAndroidDynamicSection()
                        addCommonDependencies()
                    }
                    is KotlinBasePluginWrapper -> {
                        tasks.withType(KotlinCompile::class.java).configureEach {
                            kotlinOptions {
                                jvmTarget = "1.8"
                            }
                        }
                    }
                }
            }
        }

        val config = project.extensions.create<MyPluginExtension>("happyPlugin")

        project.afterEvaluate {
            plugins.all {
                when (this) {
                    is LibraryPlugin -> {
                        libraryExtension.run {
                            buildFeatures {
                                viewBinding = config.viewBindingEnabled
                            }
                        }

                        enableParcelize(config)
                    }

                    is DynamicFeaturePlugin -> {
                        enableParcelize(config)

                        dynamicFeatureExtension.run {
                            viewBinding {
                                isEnabled = config.viewBindingEnabled
                            }
                        }
                    }
                }
            }
        }
    }

    private fun Project.enableParcelize(config: MyPluginExtension) {
        if (config.kotlinParcelize) {
            plugins.apply("kotlin-android-extensions")

            androidExtensions {
                features = setOf(AndroidExtensionsFeature.PARCELIZE.featureName)
            }
        }
    }

    private fun Project.addCommonPlugins() {
        plugins.apply("kotlin-android")
    }
}

private fun Project.addAndroidDynamicSection() = dynamicFeatureExtension.run {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


private fun Project.addAndroidLibrarySection() = libraryExtension.run {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = 1
        versionName = "1.0"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }

        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

private fun Project.addCommonDependencies() = dependencies {
    implementation(Libs.kotlin)
}