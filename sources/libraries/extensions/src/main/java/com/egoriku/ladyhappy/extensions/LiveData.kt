package com.egoriku.ladyhappy.extensions

import androidx.lifecycle.LiveData

inline fun <reified T> LiveData<T>.valueOrThrow() = value
        ?: throw IllegalArgumentException("LiveData value null")