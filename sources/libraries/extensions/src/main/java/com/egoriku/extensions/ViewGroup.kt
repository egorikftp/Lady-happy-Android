@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.annotation.StringRes

fun ViewGroup.firstChild(): View = this.getChildAt(0)

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

fun ViewGroup.getString(@StringRes resId: Int) = context.resources.getString(resId)

inline fun ViewGroup.getDimen(@DimenRes resId: Int) = context.resources.getDimensionPixelOffset(resId)