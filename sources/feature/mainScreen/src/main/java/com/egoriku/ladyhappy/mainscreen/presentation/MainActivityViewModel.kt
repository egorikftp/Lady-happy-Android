package com.egoriku.ladyhappy.mainscreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.ladyhappy.core.IAnalytics
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.navigation.screen.Screen
import com.egoriku.ladyhappy.mainscreen.common.TITLE_KEY
import com.egoriku.ladyhappy.mainscreen.common.TRACKING_KEY
import com.egoriku.ladyhappy.mainscreen.presentation.delegate.IThemedActivityDelegate

class MainActivityViewModel(
        private val analytics: IAnalytics,
        private val router: IRouter,
        private val themedDelegate: IThemedActivityDelegate
) : ViewModel(),
        IThemedActivityDelegate by themedDelegate {

    private val currentScreenTitle: MutableLiveData<Int> = MutableLiveData()

    val screenTitle: LiveData<Int> = currentScreenTitle

    fun replaceWith(screen: Screen) {
        currentScreenTitle.value = screen.arguments.getInt(TITLE_KEY)

        analytics.trackPageView(screen.arguments.getString(TRACKING_KEY) ?: EMPTY)
        router.replaceScreen(screen)
    }

    fun navigateTo(screen: Screen) = router.addScreenFullscreen(screen)

    fun onBackPressed() = router.back()

    fun trackInAppUpdateCanceled() = analytics.inAppUpdateCanceled()

    fun trackInAppUpdateFailed() = analytics.inAppUpdateFailed()

    fun trackInAppUpdateSuccess() = analytics.inAppUpdateSuccess()
}