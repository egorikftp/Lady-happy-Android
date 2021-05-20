package com.egoriku.ext

import java.util.*

fun Properties.propertyInt(key: String): Int {
    val property = getProperty(key)

    if (property.isNullOrEmpty()) {
        throw IllegalArgumentException("property $key is null")
    }

    return try {
        property.toInt()
    } catch (exception: NumberFormatException) {
        throw IllegalArgumentException("Cast exception for $key")
    }
}