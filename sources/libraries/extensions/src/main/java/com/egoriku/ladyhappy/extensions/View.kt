@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.extensions

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.withStyledAttributes

inline fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

inline fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

inline fun View.visible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.colorCompat(@ColorRes colorInt: Int) = context.colorCompat(colorInt)

fun View.drawableCompat(@DrawableRes drawableRes: Int) = context.drawableCompat(drawableRes)
        ?: throw Exception("Wrong drawable id $drawableRes")

fun View.withStyledAttributes(
        attributeSet: AttributeSet? = null,
        styleArray: IntArray,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0,
        block: TypedArray.() -> Unit
) = context.withStyledAttributes(
        set = attributeSet,
        attrs = styleArray,
        defStyleAttr = defStyleAttr,
        defStyleRes = defStyleRes,
        block = block
)