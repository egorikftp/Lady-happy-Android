package com.egoriku.storage.repository.landing

import com.egoriku.core.firestore.Result
import com.egoriku.core.model.ILandingModel
import com.egoriku.core.repository.ILandingRepository
import com.egoriku.network.datasource.LandingDataSource

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository {

    override suspend fun getLanding(): Result<ILandingModel> {
        val landingResult = landingDataSource.downloadLanding()

        return when (landingResult) {
            is Result.Error -> return landingResult
            is Result.Success -> Mapper.transformResult(landingResult.value)
        }
    }
}