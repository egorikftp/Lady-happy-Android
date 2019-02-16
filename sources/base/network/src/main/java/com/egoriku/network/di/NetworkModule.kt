package com.egoriku.network.di

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.network.datasource.LandingDataSource
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideLandingDataSource(firebaseFirestore: IFirebaseFirestore) = LandingDataSource(firebaseFirestore)
}