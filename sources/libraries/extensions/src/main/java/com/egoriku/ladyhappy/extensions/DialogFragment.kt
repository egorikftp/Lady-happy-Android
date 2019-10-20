package com.egoriku.ladyhappy.extensions

import androidx.annotation.ColorRes
import androidx.fragment.app.DialogFragment

fun DialogFragment.colorCompat(@ColorRes colorInt: Int) = requireContext().colorCompat(colorInt)