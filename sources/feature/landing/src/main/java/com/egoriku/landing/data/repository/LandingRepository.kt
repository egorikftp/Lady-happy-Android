package com.egoriku.landing.data.repository

import com.egoriku.landing.data.mapper.LandingMapper
import com.egoriku.network.Result
import com.egoriku.landing.data.repository.datasource.LandingDataSource
import com.egoriku.landing.domain.model.LandingModel

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository {

    override suspend fun getLanding(): Result<LandingModel> {
        val landingResult = landingDataSource.downloadLanding()

        return when (landingResult) {
            is Result.Error -> return landingResult
            is Result.Success -> LandingMapper.transformResult(landingResult.value)
        }
    }
}