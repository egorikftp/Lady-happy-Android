package com.egoriku.ladyhappy.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

open class Event<out T>(private val content: T) {
    private var hasBeenHandled = false

    fun getIfNotHandled(): T? = when {
        hasBeenHandled -> null
        else -> {
            hasBeenHandled = true
            content
        }
    }
}

class EventObserver<T>(private val onEventUnhandled: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getIfNotHandled()?.let { value ->
            onEventUnhandled(value)
        }
    }
}