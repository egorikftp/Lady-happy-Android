package com.egoriku.ext

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandler.kapt(dependencyNotation: Any) {
    add("kapt", dependencyNotation)
}

fun DependencyHandler.fullImplementation(dependencyNotation: Any) {
    add("fullImplementation", dependencyNotation)
}
fun DependencyHandler.stubImplementation(dependencyNotation: Any) {
    add("stubImplementation", dependencyNotation)
}