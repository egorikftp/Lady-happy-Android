package com.egoriku.ladyhappy.mozaik.strategy.internal

import com.egoriku.ladyhappy.mozaik.strategy.IStrategy
import com.egoriku.ladyhappy.mozaik.strategy.internal.extension.half
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.Proportion
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.StrategyData
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.height
import com.egoriku.ladyhappy.mozaik.strategy.internal.model.updateWith

class StrategyFor3 : IStrategy {

    override fun calculateWith(strategyData: StrategyData) {
        val size = strategyData.mozaikItems.size

        if (size != 3) {
            throw UnsupportedOperationException("Strategy3 supports only 3 items, but actually $size")
        }

        val halfScreen = strategyData.parentWidth.half()

        val rect0 = Proportion(
            mozaikItem = strategyData.mozaikItems[0],
            divider = strategyData.dividerSize
        ).getRect(
            width = strategyData.parentWidth
        ).also {
            strategyData.rect[0].updateWith(it)
        }

        val rect1 = Proportion(
            mozaikItem = strategyData.mozaikItems[1],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            offsetVertical = rect0.height,
            rightDivider = true,
            topDivider = true
        ).also {
            strategyData.rect[1].updateWith(it)
        }

        val rect2 = Proportion(
            mozaikItem = strategyData.mozaikItems[2],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            offsetHorizontal = rect1.offsetHorizontal,
            offsetVertical = rect0.offsetVertical,
            leftDivider = true,
            topDivider = true
        ).also {
            strategyData.rect[2].updateWith(it)
        }

        strategyData.parentHeight = rect2.offsetVertical
    }

    companion object {
        val strategy = StrategyFor3()
    }
}