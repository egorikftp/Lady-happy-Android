package com.egoriku.ladyhappy.extensions

import android.view.View
import androidx.annotation.ColorRes

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.colorCompat(@ColorRes colorInt: Int) = context.colorCompat(colorInt)