package com.egoriku.mainscreen.koin

import com.egoriku.mainscreen.presentation.dynamicfeature.DynamicFeatureViewModel
import com.egoriku.mainscreen.presentation.inAppUpdates.InAppUpdate
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityModule = module {
    viewModel {
        DynamicFeatureViewModel(androidApplication())
    }

    single {
        InAppUpdate(androidApplication())
    }
}