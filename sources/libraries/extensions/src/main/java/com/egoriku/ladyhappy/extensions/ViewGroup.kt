package com.egoriku.ladyhappy.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.firstChild(): View = this.getChildAt(0)

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)