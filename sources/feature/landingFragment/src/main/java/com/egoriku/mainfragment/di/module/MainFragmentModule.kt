package com.egoriku.mainfragment.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.repository.ILandingRepository
import com.egoriku.mainfragment.domain.interactors.LandingUseCase
import com.egoriku.mainfragment.presentation.fragment.MainPageContract
import com.egoriku.mainfragment.presentation.fragment.MainPagePresenter
import dagger.Module
import dagger.Provides

@Module
class MainFragmentModule {

    @Provides
    @FragmentScope
    fun provideMainPagePresenter(
            analyticsHelper: IAnalyticsHelper,
            landingUseCase: LandingUseCase
    ): MainPageContract.Presenter = MainPagePresenter(analyticsHelper, landingUseCase)

    @Provides
    @FragmentScope
    fun provideUseCase(landingRepository: ILandingRepository) = LandingUseCase(landingRepository)
}