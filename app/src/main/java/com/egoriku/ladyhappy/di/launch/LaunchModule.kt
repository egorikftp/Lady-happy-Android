package com.egoriku.ladyhappy.di.launch

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.presenters.impl.LaunchPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class LaunchModule {

    @Provides
    fun provideLaunchPresenter(router: Router, analyticsInterface: AnalyticsInterface) = LaunchPresenter(router, analyticsInterface)
}