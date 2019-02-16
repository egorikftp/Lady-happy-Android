package com.egoriku.featureactivitymain.di.module

import com.egoriku.core.di.ActivityScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IRouter
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract
import com.egoriku.featureactivitymain.presentation.activity.MainActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideMainActivityPresenter(router: IRouter, analytics: IAnalytics): MainActivityContract.Presenter = MainActivityPresenter(router, analytics)
}