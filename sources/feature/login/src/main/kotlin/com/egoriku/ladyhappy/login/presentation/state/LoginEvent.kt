package com.egoriku.ladyhappy.login.presentation.state

import android.content.Intent
import androidx.activity.result.IntentSenderRequest

sealed class LoginEvent {

    data class OneTap(val eventSenderRequest: IntentSenderRequest) : LoginEvent()

    data class SignWithGoogle(val signInIntent: Intent) : LoginEvent()
}