package com.egoriku.ladyhappy.di.mainpage

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.fragment.main.MainPageContract
import com.egoriku.ladyhappy.presentation.fragment.main.MainPagePresenter
import dagger.Module
import dagger.Provides

@Module
class MainPageModule {

    @Provides
    fun provideMainPagePresenter(analyticsInterface: AnalyticsInterface): MainPageContract.Presenter = MainPagePresenter(analyticsInterface)
}