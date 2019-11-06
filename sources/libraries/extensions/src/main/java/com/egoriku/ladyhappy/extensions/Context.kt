package com.egoriku.ladyhappy.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

fun Context.colorCompat(@ColorRes colorInt: Int) = ContextCompat.getColor(this, colorInt)

fun Context.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(this).inflate(layoutId, null)
