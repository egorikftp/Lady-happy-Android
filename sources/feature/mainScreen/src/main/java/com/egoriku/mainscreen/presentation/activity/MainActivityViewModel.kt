package com.egoriku.mainscreen.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.di.utils.IRouter
import com.egoriku.mainscreen.presentation.screen.base.AppScreen
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
        private val router: IRouter,
        private val analytics: IAnalytics) : ViewModel() {

    private val currentScreen: MutableLiveData<AppScreen> = MutableLiveData()

    val screen: LiveData<AppScreen>
        get() = currentScreen

    fun navigateTo(screen: AppScreen) {
        currentScreen.value = screen

        analytics.trackPageView(screen.trackingKey)
        router.replaceScreen(screen)
    }

    fun onBackPressed() = router.exit()
}