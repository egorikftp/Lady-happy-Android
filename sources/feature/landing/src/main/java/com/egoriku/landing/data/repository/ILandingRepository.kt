package com.egoriku.landing.data.repository

import com.egoriku.network.firestore.Result
import com.egoriku.landing.domain.model.LandingModel

interface ILandingRepository {

    suspend fun getLanding(): Result<LandingModel>
}