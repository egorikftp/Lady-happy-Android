package com.egoriku.landing.domain.interactors

import com.egoriku.landing.data.repository.ILandingRepository
import javax.inject.Inject

class LandingUseCase
@Inject constructor(private val landRepository: ILandingRepository) {

    suspend fun getLandingInfo() = landRepository.getLanding()
}