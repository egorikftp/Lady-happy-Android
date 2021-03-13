@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

inline fun <reified T : Any> Any?.castOrNull(): T? = this as? T

inline fun <reified T : Any> Any.cast(): T = this as T