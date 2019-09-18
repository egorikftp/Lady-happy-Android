package com.egoriku.ext

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}