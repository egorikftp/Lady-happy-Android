package com.egoriku.ladyhappy.common

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewTreeObserver
import kotlin.reflect.KProperty

inline fun RecyclerView.scrollPercentage(): Int {
    val offset = computeHorizontalScrollOffset()
    val extent = computeHorizontalScrollExtent()
    val range = computeHorizontalScrollRange()
     return (100.0 * offset / (range - extent).toFloat()).toInt()
}