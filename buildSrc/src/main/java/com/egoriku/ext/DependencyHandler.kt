package com.egoriku.ext

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.implementation(dependency: ProjectDependency) {
    add("implementation", dependency)
}

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
        add("kapt", dependencyNotation)
