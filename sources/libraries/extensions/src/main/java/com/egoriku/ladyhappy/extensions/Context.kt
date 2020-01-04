package com.egoriku.ladyhappy.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

fun Context.colorCompat(@ColorRes colorInt: Int) = ContextCompat.getColor(this, colorInt)

fun Context.drawableCompat(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

fun Context.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(this).inflate(layoutId, null)

fun Context.findColorIdByName(name: String): Int = getResourceId(name, type = "color")

fun Context.getResourceId(name: String, type: String): Int =
        resources.getIdentifier(name, type, packageName)

val Context.inputWindowManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
