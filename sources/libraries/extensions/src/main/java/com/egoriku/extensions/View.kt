@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.extensions

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.withStyledAttributes
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

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

fun View.colorStateListCompat(@ColorRes resId: Int): ColorStateList? = context.colorStateListCompat(resId)

fun View.colorFromAttr(@AttrRes attribute: Int) = context.colorFromAttr(attribute)

inline fun View.resIdFromAttr(@AttrRes attr: Int) = context.resIdFromAttr(attr)

fun View.drawableCompat(@DrawableRes drawableRes: Int) = context.drawableCompat(drawableRes)
        ?: throw IllegalArgumentException("Wrong drawable id $drawableRes")

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

inline fun View.indefiniteSnackBar(
        @StringRes message: Int,
        @StringRes actionText: Int,
        anchorView: View = this,
        noinline action: (View) -> Unit
) = Snackbar
        .make(this, message, Snackbar.LENGTH_INDEFINITE)
        .setAnchorView(anchorView)
        .setAction(actionText, action)
        .show()

inline fun View.longSnackBar(
        @StringRes message: Int,
        @StringRes actionText: Int,
        anchorView: View = this,
        noinline action: (View) -> Unit
) = Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .setAnchorView(anchorView)
        .setAction(actionText, action)
        .show()

inline fun View.addRipple() {
    isClickable = true

    setBackgroundResource(resIdFromAttr(android.R.attr.selectableItemBackground))
}

fun View.pxToDp(value: Int): Int =
        (value.toFloat() / context.resources.displayMetrics.density).roundToInt()