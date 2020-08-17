package com.egoriku.photoreport.koin

import com.egoriku.photoreport.data.repository.PhotoReportRepository
import com.egoriku.photoreport.domain.usecase.PhotoReportUseCase
import com.egoriku.photoreport.presentation.PhotoReportFragment
import com.egoriku.photoreport.presentation.PhotoReportViewModel
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