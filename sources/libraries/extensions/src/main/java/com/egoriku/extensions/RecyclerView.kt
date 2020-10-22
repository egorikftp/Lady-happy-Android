package com.egoriku.extensions

import androidx.recyclerview.widget.RecyclerView

fun simpleOnScrollListener(function: () -> Unit): RecyclerView.OnScrollListener {
    return object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            function.invoke()
        }
    }
}