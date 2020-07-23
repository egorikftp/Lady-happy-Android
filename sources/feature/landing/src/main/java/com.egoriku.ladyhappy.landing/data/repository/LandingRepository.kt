package com.egoriku.ladyhappy.landing.data.repository

import com.egoriku.ladyhappy.landing.data.mapper.LandingMapper
import com.egoriku.ladyhappy.landing.data.repository.datasource.LandingDataSource
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.network.ResultOf

class LandingRepository(private val landingDataSource: LandingDataSource) : ILandingRepository {

    override suspend fun getLanding(): ResultOf<LandingModel> {
        val landingResult = landingDataSource.downloadLanding()

        return when (landingResult) {
            is ResultOf.Failure -> return landingResult
            is ResultOf.Success -> LandingMapper.transformResult(landingResult.value)
        }
    }
}