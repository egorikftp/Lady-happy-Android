package com.egoriku.ladyhappy.landing.interactors

import com.egoriku.ladyhappy.landing.data.repository.ILandingRepository
import javax.inject.Inject

class LandingUseCase
@Inject constructor(private val landRepository: ILandingRepository)