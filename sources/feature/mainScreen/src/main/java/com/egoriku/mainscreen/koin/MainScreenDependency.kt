package com.egoriku.mainscreen.koin

import com.egoriku.mainscreen.domain.usecase.ObserveThemeModeUseCase
import com.egoriku.mainscreen.presentation.MainActivity
import com.egoriku.mainscreen.presentation.MainActivityViewModel
import com.egoriku.mainscreen.presentation.delegate.IThemedActivityDelegate
import com.egoriku.mainscreen.presentation.delegate.ThemedActivityDelegate
import com.egoriku.mainscreen.presentation.inAppReview.ReviewViewModel
import com.egoriku.mainscreen.presentation.viewmodel.dynamicFeature.DynamicFeatureViewModel
import com.egoriku.mainscreen.presentation.viewmodel.inAppUpdates.InAppUpdateViewModel
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityModule = module {
    single { ReviewManagerFactory.create(androidContext()) }

    scope<MainActivity> {
        scoped { ObserveThemeModeUseCase(preferences = get(), dispatchers = get()) }

        scoped<IThemedActivityDelegate> {
            ThemedActivityDelegate(observeThemeUseCase = get())
        }

        scoped {
            AppUpdateManagerFactory.create(androidContext())
        }

        scoped {
            SplitInstallManagerFactory.create(androidContext())
        }

        viewModel {
            DynamicFeatureViewModel(splitInstallManager = get())
        }

        viewModel {
            InAppUpdateViewModel(updateManager = get())
        }

        viewModel {
            MainActivityViewModel(
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