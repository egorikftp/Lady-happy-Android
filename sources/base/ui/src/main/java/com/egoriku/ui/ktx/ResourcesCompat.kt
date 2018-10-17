package com.egoriku.ui.ktx

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.appcompat.content.res.AppCompatResources

fun Context.colorCompat(@ColorRes colorInt: Int) = ContextCompat.getColor(this, colorInt)

fun Context.drawableCompat(@DrawableRes resId: Int) = AppCompatResources.getDrawable(this, resId)

fun Context.colorStateListCompat(@ColorRes colorInt: Int) = ContextCompat.getColorStateList(this, colorInt)