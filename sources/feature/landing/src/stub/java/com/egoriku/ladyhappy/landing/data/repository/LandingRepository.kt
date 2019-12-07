package com.egoriku.ladyhappy.landing.data.repository

import com.egoriku.ladyhappy.landing.data.repository.datasource.LandingDataSource

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository
