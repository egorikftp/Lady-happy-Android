@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import android.util.Log

inline fun logD(message: String) {
    Log.d("kek", message)
}

inline fun <reified T> T.logE(message: String? = null, throwable: Throwable? = null) {
    Log.e(T::class.java.simpleName, message, throwable)
}

inline fun <reified T> T.logE(
    tag: String? = null,
    message: String? = null,
    throwable: Throwable? = null
) {
    if (tag.isNullOrEmpty()) {
        Log.e(T::class.java.simpleName, message, throwable)
    } else {
        Log.e(tag, message, throwable)
    }
}