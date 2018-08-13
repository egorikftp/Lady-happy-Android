package com.egoriku.mainfragment.domain.interactors

import com.egoriku.core.repository.ILandingRepository
import com.egoriku.core.usecase.BaseUseCase
import com.egoriku.core.usecase.Params
import javax.inject.Inject

class LandingUseCase
@Inject constructor(private val landRepository: ILandingRepository) : BaseUseCase() {

    override fun getObservable(params: Params) = landRepository.getLandingInfo()
}