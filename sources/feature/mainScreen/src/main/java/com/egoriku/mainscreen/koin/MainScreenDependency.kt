package com.egoriku.mainscreen.koin

import com.egoriku.mainscreen.presentation.dynamicfeature.DynamicFeatureViewModule
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainScreenDependency {
    val module = module {
        viewModel {
            DynamicFeatureViewModule(androidApplication())
        }
    }
}