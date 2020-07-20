package com.egoriku.mozaik.strategy

import com.egoriku.mozaik.strategy.internal.*

object StrategyResolver {

    fun resolveBySize(size: Int): IStrategy = when (size) {
        1 -> StrategyFor1.strategy
        2 -> StrategyFor2.strategy
        3 -> StrategyFor3.strategy
        4 -> StrategyFor4.strategy
        5 -> StrategyFor5.strategy
        else -> StrategyFor5.strategy
    }
}