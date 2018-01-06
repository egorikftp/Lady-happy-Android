package com.egoriku.ladyhappy.di.activity

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityPresenter(router: Router, analyticsInterface: AnalyticsInterface): MainActivityContract.Presenter = MainActivityPresenter(router, analyticsInterface)
}