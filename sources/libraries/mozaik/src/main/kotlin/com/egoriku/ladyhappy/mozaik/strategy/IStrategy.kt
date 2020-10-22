package com.egoriku.ladyhappy.mozaik.strategy

import com.egoriku.ladyhappy.mozaik.strategy.internal.model.StrategyData

interface IStrategy {

    fun calculateWith(strategyData: StrategyData)
}