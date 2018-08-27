package com.egoriku.ui.ktx

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}
