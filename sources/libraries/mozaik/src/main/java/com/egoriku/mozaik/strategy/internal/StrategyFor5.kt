package com.egoriku.mozaik.strategy.internal

import com.egoriku.mozaik.strategy.IStrategy
import com.egoriku.mozaik.strategy.internal.extension.half
import com.egoriku.mozaik.strategy.internal.extension.third
import com.egoriku.mozaik.strategy.internal.model.Proportion
import com.egoriku.mozaik.strategy.internal.model.StrategyData

class StrategyFor5 : IStrategy {

    override fun calculateWith(strategyData: StrategyData) {
        val size = strategyData.mozaikItems.size

        if (size < 5) {
            throw UnsupportedOperationException("Strategy5 supports only 5 items, but actually $size")
        }

        val halfScreen = strategyData.parentWidth.half()
        val thirdScreen = strategyData.parentWidth.third()

        val rect0 = Proportion(
            mozaikItem = strategyData.mozaikItems[0],
            divider = strategyData.dividerSize
        ).getRect(
            width = thirdScreen,
            rightDivider = true
        ).also {
            strategyData.rect[0].set(it)
        }

        val rect1 = Proportion(
            mozaikItem = strategyData.mozaikItems[1],
            divider = strategyData.dividerSize / 2
        ).getRect(
            width = thirdScreen,
            offsetHorizontal = rect0.offsetHorizontal,
            leftDivider = true,
            rightDivider = true
        ).also {
            strategyData.rect[1].set(it)
        }

        Proportion(
            mozaikItem = strategyData.mozaikItems[2],
            divider = strategyData.dividerSize
        ).getRect(
            width = thirdScreen,
            offsetHorizontal = rect1.offsetHorizontal,
            leftDivider = true
        ).also {
            strategyData.rect[2].set(it)
        }

        val rect3 = Proportion(
            mozaikItem = strategyData.mozaikItems[3],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            offsetVertical = rect0.offsetVertical,
            topDivider = true,
            rightDivider = true
        )
        strategyData.rect[3].set(rect3)

        Proportion(
            mozaikItem = strategyData.mozaikItems[4],
            divider = strategyData.dividerSize
        ).getRect(
            width = halfScreen,
            offsetHorizontal = rect3.offsetHorizontal,
            offsetVertical = rect0.offsetVertical,
            topDivider = true,
            leftDivider = true
        ).also {
            strategyData.rect[4].set(it)
        }

        strategyData.parentHeight = rect3.offsetVertical
    }

    companion object {
        val strategy = StrategyFor5()
    }
}