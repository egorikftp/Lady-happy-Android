package com.egoriku.mozaik.strategy.internal.extension

import kotlin.math.roundToInt

internal fun Int.half() = (this.toFloat() / 2).roundToInt()
internal fun Int.third() = (this.toFloat() / 3).roundToInt()