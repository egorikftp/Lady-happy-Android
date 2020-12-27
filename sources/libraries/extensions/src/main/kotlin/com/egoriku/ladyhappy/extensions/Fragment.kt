package com.egoriku.ladyhappy.extensions

import android.content.res.ColorStateList
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.colorFromAttr(@AttrRes attribute: Int) = requireContext().colorFromAttr(attribute)

fun Fragment.colorCompat(@ColorRes colorInt: Int) = requireContext().colorCompat(colorInt)

fun Fragment.colorStateListCompat(@ColorRes resId: Int): ColorStateList? = requireContext().colorStateListCompat(resId)

fun Fragment.drawableCompat(@DrawableRes drawableRes: Int) = requireContext().drawableCompat(drawableRes)

fun Fragment.findColorIdByName(name: String): Int = requireContext().findColorIdByName(name)

fun Fragment.hideSoftKeyboard(flag: Int = 0) {
    view?.run {
        context?.inputWindowManager?.run {
            if (isActive) {
                hideSoftInputFromWindow(windowToken, flag)
            }
        }
    }
}

inline fun <reified T : Any> Fragment.extra(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> Fragment.extraNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}