package com.egoriku.ladyhappy.common

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import com.egoriku.featureactivitymain.presentation.activity.MainActivity

fun RecyclerView.scrollPercentage(): Int {
    val offset = computeHorizontalScrollOffset()
    val extent = computeHorizontalScrollExtent()
    val range = computeHorizontalScrollRange()
    return (100.0 * offset / (range - extent).toFloat()).toInt()
}

inline fun <reified T : Any> FragmentActivity.cast(): T {
    return when (T::class) {
        MainActivity::class -> this as T
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

fun <T> List<T>.second(): T {
    if (isEmpty())
        throw NoSuchElementException("List is empty.")
    return this[1]
}