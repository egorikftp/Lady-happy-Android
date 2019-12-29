package com.egoriku.ladyhappy.settings.presentation

import androidx.lifecycle.ViewModel
import com.egoriku.ladyhappy.auth.Authentication

class SettingsViewModel(
        private val authentication: Authentication
) : ViewModel() {

    val userLoginState = authentication.userLoginState

    fun logOut() = authentication.logOut()
}