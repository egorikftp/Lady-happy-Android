package com.egoriku.mainscreen.koin

import com.egoriku.mainscreen.domain.usecase.ObserveThemeModeUseCase
import com.egoriku.mainscreen.presentation.MainActivity
import com.egoriku.mainscreen.presentation.MainActivityViewModel
import com.egoriku.mainscreen.presentation.delegate.IThemedActivityDelegate
import com.egoriku.mainscreen.presentation.delegate.ThemedActivityDelegate
import com.egoriku.mainscreen.presentation.dynamicfeature.DynamicFeatureViewModel
import com.egoriku.mainscreen.presentation.inAppUpdates.InAppUpdate
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityModule = module {
    single { InAppUpdate(androidApplication()) }

    scope<MainActivity> {
        scoped { ObserveThemeModeUseCase(preferences = get(), dispatchers = get()) }

        scoped<IThemedActivityDelegate> {
            ThemedActivityDelegate(observeThemeUseCase = get())
        }

        viewModel {
            DynamicFeatureViewModel(androidApplication())
        }

        viewModel {
            MainActivityViewModel(
                    analytics = get(),
                    router = get(),
                    themedDelegate = get()
            )
        }
    }
}