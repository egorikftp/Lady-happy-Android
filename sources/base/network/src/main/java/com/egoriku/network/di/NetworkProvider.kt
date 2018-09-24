package com.egoriku.network.di

import com.egoriku.network.datasource.LandingDataSource
import com.egoriku.network.datasource.PhotoReportDataSource

interface NetworkProvider {

    fun provideLandingDataSource(): LandingDataSource

    fun providePhotoReportDataSource(): PhotoReportDataSource
}