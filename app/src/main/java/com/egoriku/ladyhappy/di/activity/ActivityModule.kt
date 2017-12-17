package com.egoriku.ladyhappy.di.activity

import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.presentation.presenters.impl.LaunchPresenter
import com.egoriku.ladyhappy.presentation.presenters.impl.MainActivityPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class ActivityModule {

    @Provides
    fun provideLaunchPresenter(router: Router, analyticsInterface: AnalyticsInterface) = LaunchPresenter(router, analyticsInterface)

}