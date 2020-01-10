package com.egoriku.ladyhappy.extensions

import android.os.Build

inline fun toApi(toVersion: Int, inclusive: Boolean = false, action: () -> Unit) {
    if (Build.VERSION.SDK_INT < toVersion || (inclusive && Build.VERSION.SDK_INT == toVersion)) action()
}

inline fun fromApi(fromVersion: Int, inclusive: Boolean = true, action: () -> Unit) {
    if (Build.VERSION.SDK_INT > fromVersion || (inclusive && Build.VERSION.SDK_INT == fromVersion)) action()
}

fun hasM() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M