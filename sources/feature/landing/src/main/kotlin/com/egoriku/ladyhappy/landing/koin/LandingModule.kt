package com.egoriku.ladyhappy.landing.koin

import com.egoriku.ladyhappy.landing.data.repository.ILandingRepository
import com.egoriku.ladyhappy.landing.data.repository.LandingRepository
import com.egoriku.ladyhappy.landing.domain.usecase.ILandingUseCase
import com.egoriku.ladyhappy.landing.domain.usecase.LandingUseCase
import com.egoriku.ladyhappy.landing.presentation.LandingPageFragment
import com.egoriku.ladyhappy.landing.presentation.LandingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val landingModule = module {

    scope<LandingPageFragment> {
        scoped<ILandingRepository> { LandingRepository(firebase = get()) }

        scoped<ILandingUseCase> { LandingUseCase(landRepository = get()) }

        viewModel {
            LandingViewModel(
                analytics = get(),
                landingUseCase = get()
            )
        }
    }
}