package com.egoriku.plugin.internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.DynamicFeatureExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.the

internal val Project.libs
    get() = the<LibrariesForLibs>()

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.getByType()

internal val Project.applicationExtension: ApplicationExtension
    get() = extensions.getByType()

internal val Project.dynamicFeatureExtension: DynamicFeatureExtension
    get() = extensions.getByType()