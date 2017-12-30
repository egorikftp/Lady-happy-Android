package com.egoriku.ladyhappy.di.activity

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchContract
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchPresenter
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class ActivityModule {

    @Provides
    fun provideLaunchPresenter(router: Router, analyticsInterface: AnalyticsInterface): LaunchContract.Presenter
            = LaunchPresenter(router, analyticsInterface)

    @Provides
    fun provideMainActivityPresenter(router: Router, analyticsInterface: AnalyticsInterface): MainActivityContract.Presenter
            = MainActivityPresenter(router, analyticsInterface)
}