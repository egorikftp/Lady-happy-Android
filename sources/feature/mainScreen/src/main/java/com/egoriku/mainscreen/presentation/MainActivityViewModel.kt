package com.egoriku.mainscreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.navigation.screen.Screen
import com.egoriku.mainscreen.common.TITLE_KEY
import com.egoriku.mainscreen.common.TRACKING_KEY
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
        private val router: IRouter,
        private val analytics: IAnalytics) : ViewModel() {

    private val currentScreenTitle: MutableLiveData<Int> = MutableLiveData()

    val screenTitle: LiveData<Int> = currentScreenTitle

    fun replaceWith(screen: Screen) {
        currentScreenTitle.value = screen.arguments.getInt(TITLE_KEY)

        analytics.trackPageView(screen.arguments.getString(TRACKING_KEY) ?: EMPTY)
        router.replaceScreen(screen)
    }

    fun navigateTo(screen: Screen) {
        analytics.trackPageView(screen.arguments.getString(TRACKING_KEY) ?: EMPTY)
        router.addScreen(screen)
    }

    fun onBackPressed() = router.back()
}