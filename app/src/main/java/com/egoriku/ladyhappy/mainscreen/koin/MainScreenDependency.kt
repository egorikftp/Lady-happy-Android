package com.egoriku.ladyhappy.mainscreen.koin

import com.egoriku.ladyhappy.mainscreen.domain.usecase.ObserveThemeModeUseCase
import com.egoriku.ladyhappy.mainscreen.presentation.MainActivity
import com.egoriku.ladyhappy.mainscreen.presentation.MainActivityViewModel
import com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicFeature.DynamicFeatureViewModel
import com.egoriku.ladyhappy.mainscreen.presentation.components.inAppReview.ReviewViewModel
import com.egoriku.ladyhappy.mainscreen.presentation.components.inAppUpdates.InAppUpdateViewModel
import com.egoriku.ladyhappy.mainscreen.presentation.delegate.IThemedActivityDelegate
import com.egoriku.ladyhappy.mainscreen.presentation.delegate.ThemedActivityDelegate
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityModule = module {
    single { AppUpdateManagerFactory.create(androidContext()) }
    single { ReviewManagerFactory.create(androidContext()) }
    single { SplitInstallManagerFactory.create(androidContext()) }

    scope<MainActivity> {
        scoped { ObserveThemeModeUseCase(preferences = get(), dispatchers = get()) }

        scoped<IThemedActivityDelegate> {
            ThemedActivityDelegate(observeThemeUseCase = get())
        }

        viewModel {
            DynamicFeatureViewModel(splitInstallManager = get())
        }

        viewModel {
            InAppUpdateViewModel(updateManager = get())
        }

        viewModel {
            MainActivityViewModel(
                    savedStateHandle = get(),
                    analytics = get(),
                    router = get(),
                    themedDelegate = get()
            )
        }

        viewModel {
            ReviewViewModel(
                    reviewManager = get(),
                    appPreferences = get()
            )
        }
    }
}