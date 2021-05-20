package com.egoriku.ladyhappy.mozaik.strategy.internal

import com.egoriku.ladyhappy.mozaik.strategy.IStrategy
import com.egoriku.ladyhappy.mozaik.strategy.internal.extension.half
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.Proportion
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.StrategyData
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.height
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.updateWith

class StrategyFor2 : IStrategy {

    override fun calculateWith(strategyData: StrategyData) {
        val size = strategyData.mozaikItems.size

        if (size != 2) {
            throw UnsupportedOperationException("Strategy2 supports only 2 item, but actually $size")
        }

        val halfScreen = strategyData.parentWidth.half()

        val rect0 = Proportion(
            mozaikItem = strategyData.mozaikItems[0],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            rightDivider = true
        ).also {
            strategyData.rect[0].updateWith(it)
        }

        Proportion(
            mozaikItem = strategyData.mozaikItems[1],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            leftDivider = true,
            offsetHorizontal = rect0.offsetHorizontal
        ).also {
            strategyData.rect[1].updateWith(it)
        }

        strategyData.parentHeight = rect0.height
    }

    companion object {
        val strategy = StrategyFor2()
    }
}