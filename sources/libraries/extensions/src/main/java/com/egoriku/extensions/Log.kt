package com.egoriku.extensions

import android.util.Log
import com.egoriku.extensions.common.Constants.EMPTY

inline fun <reified T> T.logD(message: String? = EMPTY) {
    when {
        message.isNullOrEmpty() -> Log.d(T::class.java.simpleName, EMPTY)
        else -> Log.d(T::class.java.simpleName, message)
    }
}

inline fun <reified T> T.logDm(message: String? = EMPTY) {
    when {
        message.isNullOrEmpty() -> Log.d("egorikftp", EMPTY)
        else -> Log.d("egorikftp", message)
    }
}