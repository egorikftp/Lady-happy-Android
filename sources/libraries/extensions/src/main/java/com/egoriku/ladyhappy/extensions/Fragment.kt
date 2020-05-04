package com.egoriku.ladyhappy.extensions

import android.content.res.ColorStateList
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.colorAttribute(@AttrRes attribute: Int) = requireContext().colorAttribute(attribute)

fun Fragment.colorCompat(@ColorRes colorInt: Int) = requireContext().colorCompat(colorInt)

fun Fragment.colorStateListCompat(@ColorRes resId: Int): ColorStateList? = requireContext().colorStateListCompat(resId)

fun Fragment.drawableCompat(@DrawableRes drawableRes: Int) = requireContext().drawableCompat(drawableRes)

fun Fragment.findColorIdByName(name: String): Int = requireContext().findColorIdByName(name)

fun Fragment.hideSoftKeyboard(flag: Int = 0) {
    view?.let { view ->
        view.context?.inputWindowManager?.run {
            if (isActive) {
                hideSoftInputFromWindow(view.windowToken, flag)
            }
        }
    }
}