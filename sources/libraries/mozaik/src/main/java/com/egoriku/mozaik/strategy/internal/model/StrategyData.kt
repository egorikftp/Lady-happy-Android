package com.egoriku.mozaik.strategy.internal.model

import com.egoriku.mozaik.model.MozaikItem

data class StrategyData(
    var mozaikItems: List<MozaikItem> = emptyList(),
    var rect: List<Rect> = emptyList(),
    var parentWidth: Int = 0,
    var parentHeight: Int = 0,
    var dividerSize: Int = 0
)