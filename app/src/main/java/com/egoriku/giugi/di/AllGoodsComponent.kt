package com.egoriku.giugi.di

import com.egoriku.giugi.di.scope.ActivityScope
import com.egoriku.giugi.mvp.main.fragment.allgoods.AllGoodsPresenter
import com.egoriku.giugi.presentation.ui.fragments.AllGoodsFragment
import dagger.Component

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(AllGoodsComponent::class))
public interface AllGoodsComponent {

    //fragments
    fun inject(allGoodsFragment: AllGoodsFragment)

    //presenters
    fun inject(allGoodsPresenter: AllGoodsPresenter)

}