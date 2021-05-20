package com.egoriku.ladyhappy.mozaik.strategy.internal

import com.egoriku.ladyhappy.mozaik.strategy.IStrategy
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.Proportion
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.StrategyData
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.height
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.updateWith

class StrategyFor1 : IStrategy {

    override fun calculateWith(strategyData: StrategyData) {
        val size = strategyData.mozaikItems.size

        if (size != 1) {
            throw UnsupportedOperationException("Strategy1 supports only 1 item, but actually $size")
        }

        val rect = Proportion(
            mozaikItem = strategyData.mozaikItems[0],
            divider = 0
        ).getRect(width = strategyData.parentWidth).also {
            strategyData.rect[0].updateWith(it)
        }

        strategyData.parentHeight = rect.height
    }

    companion object {
        val strategy = StrategyFor1()
    }
}