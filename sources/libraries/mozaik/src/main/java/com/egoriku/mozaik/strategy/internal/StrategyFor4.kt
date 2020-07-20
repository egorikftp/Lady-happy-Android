package com.egoriku.mozaik.strategy.internal

import com.egoriku.mozaik.strategy.IStrategy
import com.egoriku.mozaik.strategy.internal.extension.half
import com.egoriku.mozaik.strategy.internal.model.Proportion
import com.egoriku.mozaik.strategy.internal.model.StrategyData

class StrategyFor4 : IStrategy {

    override fun calculateWith(strategyData: StrategyData) {
        val size = strategyData.mozaikItems.size

        if (size != 4) {
            throw UnsupportedOperationException("Strategy4 supports only 4 items, but actually $size")
        }

        val halfScreen = strategyData.parentWidth.half()

        val rect0 = Proportion(
            mozaikItem = strategyData.mozaikItems[0],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            rightDivider = true
        ).also {
            strategyData.rect[0].set(it)
        }

        Proportion(
            mozaikItem = strategyData.mozaikItems[1],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            offsetHorizontal = rect0.offsetHorizontal,
            leftDivider = true
        ).also {
            strategyData.rect[1].set(it)
        }

        val rect2 = Proportion(
            mozaikItem = strategyData.mozaikItems[2],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            offsetVertical = rect0.offsetVertical,
            topDivider = true,
            rightDivider = true
        ).also {
            strategyData.rect[2].set(it)
        }

        Proportion(
            mozaikItem = strategyData.mozaikItems[3],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            offsetHorizontal = rect2.offsetHorizontal,
            offsetVertical = rect0.offsetVertical,
            topDivider = true,
            leftDivider = true
        ).also {
            strategyData.rect[3].set(it)
        }

        strategyData.parentHeight = rect2.offsetVertical
    }

    companion object {
        val strategy = StrategyFor4()
    }
}