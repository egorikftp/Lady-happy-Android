package com.egoriku.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Deprecated("should be removed")
fun Project.withLibraries(vararg libs: String) {
    dependencies {
        libs.forEach {
            implementation(it)
        }
    }
}

@Deprecated("should be removed")
fun Project.withProjects(vararg projects: String) {
    dependencies {
        projects.forEach {
            implementation(project(it))
        }
    }
}