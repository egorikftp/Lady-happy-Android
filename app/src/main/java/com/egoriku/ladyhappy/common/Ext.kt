package com.egoriku.ladyhappy.common

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewTreeObserver
import kotlin.reflect.KProperty

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

inline fun RecyclerView.scrollPercentage(): Int {
    val offset = computeHorizontalScrollOffset()
    val extent = computeHorizontalScrollExtent()
    val range = computeHorizontalScrollRange()
     return (100.0 * offset / (range - extent).toFloat()).toInt()
}