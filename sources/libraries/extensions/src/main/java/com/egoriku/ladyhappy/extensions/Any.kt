package com.egoriku.ladyhappy.extensions

inline fun <reified T : Any> Any?.castOrNull(): T? = this as? T