package com.egoriku.ladyhappy.core

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchers {

    val io: CoroutineDispatcher

    val mainImmediate: CoroutineDispatcher

    val default: CoroutineDispatcher
}