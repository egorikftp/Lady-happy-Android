package com.egoriku.ext

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandler.kapt(dependencyNotation: Any) {
    add("kapt", dependencyNotation)
}