package com.egoriku.ext

import org.gradle.api.GradleException
import org.jetbrains.kotlin.konan.properties.Properties

fun Properties.propertyInt(key: String): Int {
    val property = getProperty(key)

    if (property.isNullOrEmpty()) {
        throw GradleException("property $key is null")
    }

    return try {
        property.toInt()
    } catch (exception: NumberFormatException) {
        throw GradleException("Cast exception for $key")
    }
}