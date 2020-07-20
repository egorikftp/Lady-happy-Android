package com.egoriku.mozaik.strategy

import com.egoriku.mozaik.strategy.internal.model.StrategyData

interface IStrategy {

    fun calculateWith(strategyData: StrategyData)
}