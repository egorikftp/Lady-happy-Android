package com.egoriku.ladyhappy.extensions

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}
