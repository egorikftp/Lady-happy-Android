package com.egoriku.application

import com.egoriku.ext.propertyInt
import org.gradle.api.Project
import org.jetbrains.kotlin.konan.properties.Properties
import org.jetbrains.kotlin.konan.properties.loadProperties

fun Project.provideVersionCode(): Int {
    val properties: Properties = loadProperties("$rootDir/app/version.properties")
    val major = properties.propertyInt("VERSION")
    val minor = properties.propertyInt("SUB_VERSION")
    val patch = properties.propertyInt("BUILD_VERSION")

    return calcVersionCode(major, minor, patch)
}

fun Project.provideVersionName(): String {
    val properties: Properties = loadProperties("$rootDir/app/version.properties")
    val major = properties.propertyInt("VERSION")
    val minor = properties.propertyInt("SUB_VERSION")
    val patch = properties.propertyInt("BUILD_VERSION")

    return "$major.$minor.$patch"
}

private fun calcVersionCode(major: Int, minor: Int, patch: Int): Int = major * 100000 + minor * 1000 + patch
