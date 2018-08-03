package com.egoriku.storage.di

import com.egoriku.core.repository.ILandingRepository
import com.egoriku.storage.LandingRepository
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideLandingRepo(): ILandingRepository = LandingRepository()
}