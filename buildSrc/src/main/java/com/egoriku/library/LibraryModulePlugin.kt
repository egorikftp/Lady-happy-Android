@file:Suppress("unused")

package com.egoriku.library

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.versions.ProjectVersion
import com.egoriku.ext.implementation
import com.egoriku.ext.release
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

open class LibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        configurePlugins()
        configureAndroid()

        dependencies {
            implementation(Libs.kotlin)
        }
    }
}

fun Project.configurePlugins() {
    with(plugins) {
        apply("com.android.library")
        apply("kotlin-android")
    }
}

private fun Project.configureAndroid() {
    (extensions.getByName("android") as? BaseExtension)?.apply {
        when (this) {
            is LibraryExtension -> {
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
                }

                viewBinding {
                    isEnabled = true
                }
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        project.tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}