package com.egoriku.ladyhappy.extensions

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.colorCompat(@ColorRes colorInt: Int) = requireContext().colorCompat(colorInt)

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