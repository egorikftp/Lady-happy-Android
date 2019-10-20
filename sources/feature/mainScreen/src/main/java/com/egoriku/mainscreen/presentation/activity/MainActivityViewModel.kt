package com.egoriku.mainscreen.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.navigation.screen.Screen
import com.egoriku.mainscreen.common.TRACKING_KEY
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
        private val router: IRouter,
        private val analytics: IAnalytics) : ViewModel() {

    private val currentScreen: MutableLiveData<Screen> = MutableLiveData()

    val screen: LiveData<Screen> = currentScreen

    fun replaceWith(screen: Screen) {
        currentScreen.value = screen

        analytics.trackPageView(screen.arguments.getString(TRACKING_KEY) ?: EMPTY)
        router.replaceScreen(screen)
    }

    fun onBackPressed() = router.back()
}