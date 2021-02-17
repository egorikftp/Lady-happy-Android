package com.egoriku.ladyhappy.landing.domain.usecase

import com.egoriku.ladyhappy.landing.data.mapper.LandingMapper
import com.egoriku.ladyhappy.landing.data.repository.ILandingRepository
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.ladyhappy.network.ResultOf

internal class LandingUseCase(
        private val landRepository: ILandingRepository
) : ILandingUseCase {

    override suspend fun getLandingInfo() =
            when (val landingResult = landRepository.getLanding()) {
                is ResultOf.Failure -> landingResult
                is ResultOf.Success -> LandingMapper.transformResult(landingResult.value)
            }
}

interface ILandingUseCase {

    suspend fun getLandingInfo(): ResultOf<LandingModel>
}