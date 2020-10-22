package com.egoriku.extensions

import androidx.lifecycle.Observer

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