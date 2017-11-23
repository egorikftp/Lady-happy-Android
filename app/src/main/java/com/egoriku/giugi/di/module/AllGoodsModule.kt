package com.egoriku.giugi.di.module

import android.support.v4.app.Fragment
import com.egoriku.giugi.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.giugi.external.AnalyticsInterface
import com.egoriku.giugi.presentation.presenters.impl.AllGoodsPresenterNew
import dagger.Module
import dagger.Provides

@Module
class AllGoodsModule(private val fragment: Fragment) {

    @Provides
    fun providesFragment() = fragment

    @Provides
    fun providesAllGoodsPresenter(analyticsInterface: AnalyticsInterface, useCase: CategoriesUseCase): AllGoodsPresenterNew {
        return AllGoodsPresenterNew(analyticsInterface, useCase)
    }

}