package com.egoriku.mainscreen.di.module

import com.egoriku.core.di.ActivityScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IRouter
import com.egoriku.mainscreen.presentation.activity.MainActivityContract
import com.egoriku.mainscreen.presentation.activity.MainActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideMainActivityPresenter(router: IRouter, analytics: IAnalytics): MainActivityContract.Presenter = MainActivityPresenter(router, analytics)
}