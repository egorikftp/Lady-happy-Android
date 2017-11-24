package com.egoriku.ladyhappy.mvp.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DrawerNavigationView : MvpView {

    companion object {
        const val ALL_GOODS_POSITION = 0
        const val ORDER_POSITION = 1
        const val SHARE_POSITION = 2
        const val FEEDBACK_POSITION = 3

        const val CREATE_NEW_POST_POSITION = 4
    }

    fun selectDrawerItem(position: Int)
}
