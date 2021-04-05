package com.egoriku.ladyhappy.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun AppCompatActivity.repeatingJobOnStarted(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
) {
    addRepeatingJob(Lifecycle.State.STARTED, coroutineContext, block)
}

fun Fragment.repeatingJobOnStarted(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED, coroutineContext, block)
}