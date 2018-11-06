package com.egoriku.network.di

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.network.datasource.LandingDataSource
import com.egoriku.network.datasource.LandingDataSourceExperimental
import com.egoriku.network.datasource.PhotoReportDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    @Named("OLD")
    fun provideLandingDataSource(firebaseFirestore: IFirebaseFirestore) = LandingDataSource(firebaseFirestore)

    @Provides
    @Named("EXPERIMENTAL")
    fun provideExperimentalLandingDataSource(firebaseFirestore: IFirebaseFirestore) = LandingDataSourceExperimental(firebaseFirestore)

    @Provides
    fun providePhotoReportDataSource(firebaseFirestore: IFirebaseFirestore) = PhotoReportDataSource(firebaseFirestore)
}