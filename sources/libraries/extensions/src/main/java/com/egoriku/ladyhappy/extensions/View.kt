@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import android.view.View
import androidx.annotation.ColorRes

inline fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

inline fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

inline fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

inline fun View.colorCompat(@ColorRes colorInt: Int) = context.colorCompat(colorInt)