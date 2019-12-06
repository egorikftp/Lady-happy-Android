package com.egoriku.ladyhappy.extensions

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes

fun View.gone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

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