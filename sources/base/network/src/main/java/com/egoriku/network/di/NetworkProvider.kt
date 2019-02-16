package com.egoriku.network.di

import com.egoriku.network.datasource.LandingDataSource

interface NetworkProvider {

    fun provideLandingDataSource(): LandingDataSource
}