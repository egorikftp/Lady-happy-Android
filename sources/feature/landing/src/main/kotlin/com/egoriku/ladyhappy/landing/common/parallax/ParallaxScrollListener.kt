package com.egoriku.ladyhappy.landing.common.parallax

import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView

open class ParallaxScrollListener : RecyclerView.OnScrollListener(), OnGlobalLayoutListener, LifecycleObserver {

    private val scrollListeners = mutableListOf<ParallaxScrollStateListener>()
    private var recyclerView: RecyclerView? = null

    var recyclerViewHeight = 0
        private set

    fun initWith(view: RecyclerView, lifecycle: Lifecycle) {
        recyclerView = view
        recyclerView?.viewTreeObserver?.addOnGlobalLayoutListener(this)
        recyclerView?.addOnScrollListener(this)
        lifecycle.addObserver(this)
    }

    fun addListener(scrollListenerListener: ParallaxScrollStateListener) {
        scrollListeners.add(scrollListenerListener)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        scrollListeners.forEach { scrollListener ->
            scrollListener.onScroll()
        }
    }

    override fun onGlobalLayout() {
        var height = 0
        if (recyclerView != null) {
            height = recyclerView!!.height
        }
        if (height > 0) {
            recyclerViewHeight = height
            recyclerView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)

            scrollListeners.forEach { scrollListener ->
                scrollListener.onAttach()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun detach() {
        scrollListeners.forEach { scrollListener ->
            scrollListener.onDetach()
        }

        recyclerView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
        recyclerView?.removeOnScrollListener(this)
        scrollListeners.clear()
        recyclerView = null
    }
}