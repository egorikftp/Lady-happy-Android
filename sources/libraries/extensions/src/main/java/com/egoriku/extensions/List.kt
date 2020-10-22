package com.egoriku.extensions

fun <T> List<T>.second(): T {
    when {
        isEmpty() -> throw NoSuchElementException("List is empty.")
        else -> return this[1]
    }
}