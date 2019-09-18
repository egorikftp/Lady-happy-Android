package com.egoriku

import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

@Suppress("UNCHECKED_CAST")
fun <T> ExtensionAware.getExtensionByName(name: String): T {
    return extensions.getByName(name) as T
}

fun Project.toExtensionAware() = this as ExtensionAware