package com.egoriku.landing.koin

import com.egoriku.landing.data.repository.LandingRepository
import com.egoriku.landing.domain.usecase.LandingUseCase
import com.egoriku.landing.presentation.LandingPageFragment
import com.egoriku.landing.presentation.LandingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val landingModule = module {

    scope<LandingPageFragment> {
        scoped { LandingRepository(firebaseFirestore = get()) }
        scoped { LandingUseCase(landRepository = get()) }

        viewModel {
            LandingViewModel(
                    analytics = get(),
                    landingUseCase = get()
            )
        }
    }
}