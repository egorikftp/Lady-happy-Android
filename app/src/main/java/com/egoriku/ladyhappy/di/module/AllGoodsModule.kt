package com.egoriku.ladyhappy.di.module

import android.support.v4.app.Fragment
import com.egoriku.ladyhappy.domain.interactors.allgoods.CategoriesUseCase
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.presenters.impl.AllGoodsPresenterNew
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