package com.egoriku.ui.ktx

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources

fun Context.colorCompat(@ColorRes colorInt: Int) = ContextCompat.getColor(this, colorInt)

fun Context.drawableCompat(@DrawableRes resId: Int) = AppCompatResources.getDrawable(this, resId)

fun Context.colorStateListCompat(@ColorRes colorInt: Int) = ContextCompat.getColorStateList(this, colorInt)