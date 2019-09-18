package com.egoriku.ext

import com.egoriku.getExtensionByName
import com.egoriku.toExtensionAware
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.internal.CacheImplementation

fun Project.withLibraries(vararg libs: String) {
    dependencies {
        libs.forEach {
            implementation(it)
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