package com.egoriku.usedLibraries.koin

import com.egoriku.usedLibraries.domain.usecase.LicenseUseCase
import com.egoriku.usedLibraries.presentation.UsedLibrariesFragment
import com.egoriku.usedLibraries.presentation.viewmodel.UsedLibrariesViewModel
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