package com.egoriku.core.repository

import com.egoriku.core.firestore.Result
import com.egoriku.core.model.ILandingModel

interface ILandingRepository {

    suspend fun getLanding(): Result<ILandingModel>
}