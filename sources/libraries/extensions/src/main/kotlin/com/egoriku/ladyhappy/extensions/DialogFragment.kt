package com.egoriku.ladyhappy.extensions

import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

fun DialogFragment.colorCompat(@ColorRes colorInt: Int) = requireContext().colorCompat(colorInt)

inline fun <reified T> DialogFragment.dataBySelectedPosition(): T =
        (dialog as AlertDialog).listView.run {
                adapter.getItem(checkedItemPosition) as T
        }