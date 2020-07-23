package com.egoriku.ladyhappy.landing.data.repository

import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.network.ResultOf

interface ILandingRepository {

    suspend fun getLanding(): ResultOf<LandingModel>
}