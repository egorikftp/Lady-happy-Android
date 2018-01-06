package com.egoriku.ladyhappy.di.activity

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchContract
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class LaunchActivityModule {

    @Provides
    fun provideLaunchPresenter(router: Router, analyticsInterface: AnalyticsInterface):LaunchContract.Presenter = LaunchPresenter(router, analyticsInterface)
}