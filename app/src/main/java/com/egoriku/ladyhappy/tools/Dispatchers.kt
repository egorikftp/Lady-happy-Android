package com.egoriku.ladyhappy.tools

import com.egoriku.ladyhappy.core.IDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class Dispatchers : IDispatchers {

    override val io: CoroutineDispatcher = Dispatchers.IO

    override val mainImmediate: CoroutineDispatcher = Dispatchers.Main.immediate

    override val default: CoroutineDispatcher = Dispatchers.Default
}