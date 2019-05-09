package com.egoriku.ladyhappy.extensions

import android.util.Log
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY

inline fun <reified T> T.logD(message: String? = EMPTY) {
    Log.d(T::class.java.simpleName, message)
}