package com.egoriku.ladyhappy.usedLibraries.koin

import com.egoriku.ladyhappy.usedLibraries.domain.usecase.LicenseUseCase
import com.egoriku.ladyhappy.usedLibraries.presentation.UsedLibrariesFragment
import com.egoriku.ladyhappy.usedLibraries.presentation.viewmodel.UsedLibrariesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usedLibrariesModule = module {

    scope<UsedLibrariesFragment> {
        scoped {
            LicenseUseCase(androidContext())
        }

        viewModel {
            UsedLibrariesViewModel(licenseUseCase = get())
        }
    }
}