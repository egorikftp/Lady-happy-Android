package com.egoriku.ladyhappy.landing.domain.usecase

import com.egoriku.ladyhappy.landing.data.mapper.LandingMapper
import com.egoriku.ladyhappy.landing.data.repository.LandingRepository
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.ladyhappy.network.ResultOf

class LandingUseCase(private val landRepository: LandingRepository) {

    suspend fun getLandingInfo(): ResultOf<LandingModel> {
        return when (val landingResult = landRepository.getLanding()) {
            is ResultOf.Failure -> return landingResult
            is ResultOf.Success -> LandingMapper.transformResult(landingResult.value)
        }
    }
}