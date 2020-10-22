package com.egoriku.plugin.internal

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.getByType()

internal val Project.appExtension: AppExtension
    get() = extensions.getByType()

fun Project.androidExtensions(configure: AndroidExtensionsExtension.() -> Unit): Unit =
        (this as ExtensionAware).extensions.configure("androidExtensions", configure)
