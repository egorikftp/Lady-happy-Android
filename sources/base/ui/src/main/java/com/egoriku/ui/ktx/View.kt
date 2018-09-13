package com.egoriku.ui.ktx

import android.view.View

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}