package com.egoriku.landingfragment.domain.interactors

import com.egoriku.core.repository.ILandingRepository
import com.egoriku.core.usecase.BaseUseCase
import com.egoriku.core.usecase.Params
import javax.inject.Inject

internal class LandingExperimentalUseCase
@Inject constructor(private val landRepository: ILandingRepository) {

    fun getObservable(params: Params) = landRepository.getLandingInfo()
}