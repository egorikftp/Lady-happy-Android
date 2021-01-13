package com.egoriku.ladyhappy.mainscreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.egoriku.ladyhappy.core.IAnalytics
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.mainscreen.presentation.delegate.IThemedActivityDelegate
import com.egoriku.ladyhappy.mainscreen.presentation.screen.params.ScreenParams
import com.egoriku.ladyhappy.navigation.screen.Screen

private const val KEY_SCREEN_TITLE = "KEY_SCREEN_TITLE"

class MainActivityViewModel(
        private val savedStateHandle: SavedStateHandle,
        private val analytics: IAnalytics,
        private val router: IRouter,
        private val themedDelegate: IThemedActivityDelegate
) : ViewModel(),
        IThemedActivityDelegate by themedDelegate {

    val screenTitle: LiveData<Int> = savedStateHandle.getLiveData(KEY_SCREEN_TITLE)

    fun replaceWith(screen: Screen, params: ScreenParams) {
        savedStateHandle.set(KEY_SCREEN_TITLE, params.screenNameResId)
        analytics.trackPageView(params.trackingScreenName)

        router.replaceScreen(screen)
    }

    fun navigateTo(screen: Screen) = router.addScreenFullscreen(screen)

    fun onBackPressed() = router.back()

    fun trackInAppUpdateCanceled() = analytics.inAppUpdateCanceled()

    fun trackInAppUpdateFailed() = analytics.inAppUpdateFailed()

    fun trackInAppUpdateSuccess() = analytics.inAppUpdateSuccess()
}