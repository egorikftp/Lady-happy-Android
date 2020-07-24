package com.egoriku.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

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