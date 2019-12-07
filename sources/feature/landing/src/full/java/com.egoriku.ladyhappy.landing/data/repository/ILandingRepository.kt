package com.egoriku.ladyhappy.landing.data.repository

import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.network.Result

interface ILandingRepository {

    suspend fun getLanding(): Result<LandingModel>
}