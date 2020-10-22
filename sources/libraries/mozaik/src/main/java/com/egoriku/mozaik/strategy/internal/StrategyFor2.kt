package com.egoriku.mozaik.strategy.internal

import com.egoriku.mozaik.strategy.IStrategy
import com.egoriku.mozaik.strategy.internal.extension.half
import com.egoriku.mozaik.strategy.internal.model.Proportion
import com.egoriku.mozaik.strategy.internal.model.StrategyData

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
            strategyData.rect[0].set(it)
        }

        Proportion(
                mozaikItem = strategyData.mozaikItems[1],
                divider = strategyData.dividerSize
        ).getRect(
                width = halfScreen,
                leftDivider = true,
                offsetHorizontal = rect0.offsetHorizontal
        ).also {
            strategyData.rect[1].set(it)
        }

        strategyData.parentHeight = rect0.height()
    }

    companion object {
        val strategy = StrategyFor2()
    }
}