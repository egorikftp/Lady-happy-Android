package com.egoriku.ladyhappy.extensions

import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment

fun Fragment.colorCompat(@ColorRes colorInt: Int) = requireContext().colorCompat(colorInt)