package com.egoriku.extensions

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}
