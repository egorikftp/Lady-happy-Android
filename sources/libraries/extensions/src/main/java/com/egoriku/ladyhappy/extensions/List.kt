package com.egoriku.ladyhappy.extensions

fun <T> List<T>.second(): T {
    if (isEmpty())
        throw NoSuchElementException("List is empty.")
    return this[1]
}