package com.egoriku.landingfragment.domain.interactors

import com.egoriku.core.repository.ILandingRepository
import javax.inject.Inject

internal class LandingUseCase
@Inject constructor(private val landRepository: ILandingRepository) {

    suspend fun getLandingInfo() = landRepository.getLanding()
}