package com.egoriku.giugi.mvp.main.fragment.allgoods

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AllGoodsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun setTitle(title: String)
}
