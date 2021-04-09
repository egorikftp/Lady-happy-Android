package com.egoriku.ladyhappy.mozaik.strategy.internal.model

import com.egoriku.ladyhappy.mozaik.model.MozaikItem

@Suppress("DataClassShouldBeImmutable")
data class StrategyData(
        var mozaikItems: List<MozaikItem> = emptyList(),
        var rect: List<Rect> = emptyList(),
        var parentWidth: Int = 0,
        var parentHeight: Int = 0,
        var dividerSize: Int = 0
)