package com.egoriku.ladyhappy.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, text, duration).show()

fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(requireContext(), text, duration).show()