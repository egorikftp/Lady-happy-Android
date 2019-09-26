package com.egoriku.ext

import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

fun Project.toExtensionAware() = this as ExtensionAware

fun Project.withLibraries(vararg libs: String) {
    dependencies {
        libs.forEach {
            implementation(it)
        }
    }
}

fun Project.withKapt(vararg libs: Pair<String, String>) {
    dependencies {
        libs.forEach {
            implementation(it.first)
            kapt(it.second)
        }
    }
}

fun Project.withProjects(vararg projects: String) {
    dependencies {
        projects.forEach {
            implementation(project(it))
        }
    }
}

fun Project.allowExperimentalExtensions() {
    toExtensionAware()
            .getExtensionByName<AndroidExtensionsExtension>("androidExtensions")
            .run {
                isExperimental = true
                defaultCacheImplementation = CacheImplementation.SPARSE_ARRAY
            }
}