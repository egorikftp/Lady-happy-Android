@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import android.os.Bundle

inline fun Bundle.getStringOrThrow(key: String) = requireNotNull(getString(key)) {
    "Cannot find value for $key"
}