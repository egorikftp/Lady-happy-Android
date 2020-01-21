package com.egoriku.ladyhappy.settings.presentation

import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.navigation.screen.Screen

class SettingsViewModel(
        private val authentication: Authentication,
        private val router: IRouter
) : ViewModel() {

    val userLoginState = authentication.userLoginState

    fun logOut() = authentication.logOut()

    fun navigateTo(screen: Screen, containerId: Int) = router.addScreenWithContainerId(screen, containerId)
}