package com.egoriku.ladyhappy.login.presentation.state

sealed class LoginState {

    class Progress : LoginState()
    class Success : LoginState()
    class Error(val message: String) : LoginState()
}