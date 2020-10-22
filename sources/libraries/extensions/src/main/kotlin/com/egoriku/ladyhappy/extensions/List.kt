package com.egoriku.ladyhappy.extensions

fun <T> List<T>.second(): T {
    when {
        isEmpty() -> throw NoSuchElementException("List is empty.")
        else -> return this[1]
    }
}