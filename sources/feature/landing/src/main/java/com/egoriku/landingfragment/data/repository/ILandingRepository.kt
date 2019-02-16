package com.egoriku.landingfragment.data.repository

import com.egoriku.network.firestore.Result
import com.egoriku.landingfragment.domain.model.LandingModel

interface ILandingRepository {

    suspend fun getLanding(): Result<LandingModel>
}