package com.egoriku.storage.di

import com.egoriku.core.repository.ILandingRepository
import com.egoriku.network.landing.LandingDataSource
import com.egoriku.storage.landing.LandingRepository
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideLandingRepo(landingDataSource: LandingDataSource): ILandingRepository = LandingRepository(landingDataSource)
}