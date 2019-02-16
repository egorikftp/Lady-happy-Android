package com.egoriku.landingfragment.data.repository

import com.egoriku.landingfragment.data.mapper.LandingMapper
import com.egoriku.network.firestore.Result
import com.egoriku.landingfragment.data.repository.datasource.LandingDataSource
import com.egoriku.landingfragment.domain.model.LandingModel

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository {

    override suspend fun getLanding(): Result<LandingModel> {
        val landingResult = landingDataSource.downloadLanding()

        return when (landingResult) {
            is Result.Error -> return landingResult
            is Result.Success -> LandingMapper.transformResult(landingResult.value)
        }
    }
}