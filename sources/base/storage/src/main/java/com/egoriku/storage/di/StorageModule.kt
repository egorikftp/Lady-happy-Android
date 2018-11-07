package com.egoriku.storage.di

import com.egoriku.core.repository.ILandingRepository
import com.egoriku.core.repository.IPhotoReportRepository
import com.egoriku.network.datasource.LandingDataSource
import com.egoriku.network.datasource.PhotoReportDataSource
import com.egoriku.storage.repository.landing.LandingRepository
import com.egoriku.storage.repository.photoreport.PhotoReportRepository
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideLandingRepo(landingDataSource: LandingDataSource): ILandingRepository = LandingRepository(landingDataSource)

    @Provides
    fun providePhotoReportRepo(photoReportDataSource: PhotoReportDataSource): IPhotoReportRepository = PhotoReportRepository(photoReportDataSource)
}