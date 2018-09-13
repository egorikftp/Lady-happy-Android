package com.egoriku.photoreportfragment.common

import android.support.v7.widget.RecyclerView

fun RecyclerView.scrollPercentage(): Int {
    val offset = computeHorizontalScrollOffset()
    val extent = computeHorizontalScrollExtent()
    val range = computeHorizontalScrollRange()
    return (100.0 * offset / (range - extent).toFloat()).toInt()
}