package com.egoriku.photoreportfragment.common

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollPercentage(): Int {
    val offset = computeHorizontalScrollOffset()
    val extent = computeHorizontalScrollExtent()
    val range = computeHorizontalScrollRange()
    return (100.0 * offset / (range - extent).toFloat()).toInt()
}