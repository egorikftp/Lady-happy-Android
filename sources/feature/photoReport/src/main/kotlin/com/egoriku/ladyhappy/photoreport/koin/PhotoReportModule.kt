package com.egoriku.ladyhappy.photoreport.koin

import com.egoriku.ladyhappy.photoreport.data.repository.PhotoReportRepository
import com.egoriku.ladyhappy.photoreport.domain.usecase.PhotoReportUseCase
import com.egoriku.ladyhappy.photoreport.presentation.PhotoReportFragment
import com.egoriku.ladyhappy.photoreport.presentation.PhotoReportViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoReportModule = module {

    scope<PhotoReportFragment> {
        scoped { PhotoReportRepository(firebase = get()) }
        scoped { PhotoReportUseCase(photoReportRepository = get()) }

        viewModel {
            PhotoReportViewModel(
                    photoReportUseCase = get(),
                    analytics = get()
            )
        }
    }
}