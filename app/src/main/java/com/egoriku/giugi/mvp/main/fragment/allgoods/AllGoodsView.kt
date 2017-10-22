package com.egoriku.giugi.mvp.main.fragment.allgoods

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface AllGoodsView : MvpView {

    fun setTitle(title: String)
}
