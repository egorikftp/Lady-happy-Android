package com.egoriku.plugin.internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.DynamicFeatureExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

internal val Project.libs
    get() = the<LibrariesForLibs>()

internal val Project.libraryExtension
    get() = the<LibraryExtension>()

internal val Project.applicationExtension
    get() = the<ApplicationExtension>()

internal val Project.dynamicFeatureExtension
    get() = the<DynamicFeatureExtension>()