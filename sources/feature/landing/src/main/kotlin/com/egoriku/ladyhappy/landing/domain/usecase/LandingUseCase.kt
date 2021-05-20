package com.egoriku.ladyhappy.landing.domain.usecase

import com.egoriku.ladyhappy.landing.data.mapper.LandingEntityMapper
import com.egoriku.ladyhappy.landing.data.repository.ILandingRepository
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.ladyhappy.network.ResultOf

internal class LandingUseCase(
    private val landRepository: ILandingRepository
) : ILandingUseCase {

    override suspend fun getLandingInfo() =
        when (val landingResult = landRepository.getLanding()) {
            is ResultOf.Failure -> landingResult
            is ResultOf.Success -> ResultOf.Success(
                LandingEntityMapper().invoke(landingResult.value)
            )
        }
}

interface ILandingUseCase {

    suspend fun getLandingInfo(): ResultOf<LandingModel>
}