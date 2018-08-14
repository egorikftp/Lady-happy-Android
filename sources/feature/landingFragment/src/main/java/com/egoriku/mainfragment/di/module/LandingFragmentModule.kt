package com.egoriku.mainfragment.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.repository.ILandingRepository
import com.egoriku.mainfragment.domain.interactors.LandingUseCase
import com.egoriku.mainfragment.presentation.LandingPageContract
import com.egoriku.mainfragment.presentation.LandingPagePresenter
import dagger.Module
import dagger.Provides

@Module
internal class LandingFragmentModule {

    @Provides
    @FragmentScope
    fun provideMainPagePresenter(
            analyticsHelper: IAnalyticsHelper,
            landingUseCase: LandingUseCase
    ): LandingPageContract.Presenter = LandingPagePresenter(analyticsHelper, landingUseCase)

    @Provides
    @FragmentScope
    fun provideUseCase(landingRepository: ILandingRepository) = LandingUseCase(landingRepository)
}