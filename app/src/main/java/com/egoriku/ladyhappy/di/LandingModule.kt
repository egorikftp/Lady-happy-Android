package com.egoriku.ladyhappy.di

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.landing.data.repository.ILandingRepository
import com.egoriku.ladyhappy.landing.data.repository.LandingRepository
import com.egoriku.ladyhappy.landing.data.repository.datasource.LandingDataSource

import dagger.Module
import dagger.Provides

@Module
@Deprecated("Should be inside feature after migration to Koin")
class LandingModule {

    @Provides
    fun provideLandingRepo(landingDataSource: LandingDataSource): ILandingRepository = LandingRepository(landingDataSource)

    @Provides
    fun provideLandingDataSource(firebaseFirestore: IFirebaseFirestore) = LandingDataSource(firebaseFirestore)
}