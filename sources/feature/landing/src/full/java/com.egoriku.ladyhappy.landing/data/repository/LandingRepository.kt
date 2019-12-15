package com.egoriku.ladyhappy.landing.data.repository

import com.egoriku.ladyhappy.landing.data.mapper.LandingMapper
import com.egoriku.ladyhappy.landing.data.repository.datasource.LandingDataSource
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.network.Result

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository {

    override suspend fun getLanding(): Result<LandingModel> {
        val landingResult = landingDataSource.downloadLanding()

        return when (landingResult) {
            is Result.Error -> return landingResult
            is Result.Success -> LandingMapper.transformResult(landingResult.value)
        }
    }
}