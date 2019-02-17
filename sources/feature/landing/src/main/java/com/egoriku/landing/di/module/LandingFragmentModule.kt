package com.egoriku.landing.di.module

import com.egoriku.core.di.FragmentScope
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.landing.data.repository.ILandingRepository
import com.egoriku.landing.data.repository.LandingRepository
import com.egoriku.landing.data.repository.datasource.LandingDataSource
import com.egoriku.landing.domain.interactors.LandingUseCase
import com.egoriku.landing.presentation.LandingPageContract
import com.egoriku.landing.presentation.LandingPagePresenter
import dagger.Module
import dagger.Provides

@Module
internal class LandingFragmentModule {

    @Provides
    @FragmentScope
    fun provideMainPagePresenter(
            analytics: IAnalytics,
            landingUseCase: LandingUseCase
    ): LandingPageContract.Presenter = LandingPagePresenter(analytics, landingUseCase)

    @Provides
    fun provideLandingRepo(landingDataSource: LandingDataSource): ILandingRepository = LandingRepository(landingDataSource)

    @Provides
    fun provideLandingDataSource(firebaseFirestore: IFirebaseFirestore) = LandingDataSource(firebaseFirestore)
}