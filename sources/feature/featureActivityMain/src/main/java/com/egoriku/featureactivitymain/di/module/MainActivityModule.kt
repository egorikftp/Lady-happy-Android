package com.egoriku.featureactivitymain.di.module

import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.core.actions.IPhotoReportFragmentAction
import com.egoriku.core.di.ActivityScope
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.di.utils.IRouter
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract
import com.egoriku.featureactivitymain.presentation.activity.MainActivityPresenter
import com.egoriku.featureactivitymain.presentation.screen.ScreenFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideMainActivityPresenter(router: IRouter,
                                     analyticsHelper: IAnalyticsHelper,
                                     screenFactory: ScreenFactory
    ): MainActivityContract.Presenter = MainActivityPresenter(
            router,
            analyticsHelper,
            screenFactory
    )

    @Provides
    @ActivityScope
    fun provideScreenActionFactory(landingFragmentAction: ILandingFragmentAction,
                                   photoReportFragmentAction: IPhotoReportFragmentAction
    ): ScreenFactory = ScreenFactory(
            landingFragmentAction,
            photoReportFragmentAction
    )
}