package com.egoriku.application

import com.egoriku.ext.propertyInt
import java.io.FileInputStream
import java.util.*

fun provideVersionCode(): Int {
    val properties: Properties = loadVersionProperties()
    val major = properties.propertyInt("VERSION")
    val minor = properties.propertyInt("SUB_VERSION")
    val patch = properties.propertyInt("BUILD_VERSION")

    return calcVersionCode(major, minor, patch)
}

fun provideVersionName(): String {
    val properties: Properties = loadVersionProperties()
    val major = properties.propertyInt("VERSION")
    val minor = properties.propertyInt("SUB_VERSION")
    val patch = properties.propertyInt("BUILD_VERSION")

    return "$major.$minor.$patch"
}

private fun calcVersionCode(major: Int, minor: Int, patch: Int): Int =
    major * 100000 + minor * 1000 + patch


fun loadVersionProperties(): Properties =
    FileInputStream("app/version.properties").use { inputStream ->
        Properties().apply {
            load(inputStream)
        }
    }