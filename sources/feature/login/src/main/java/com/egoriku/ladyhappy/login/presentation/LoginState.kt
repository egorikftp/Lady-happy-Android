package com.egoriku.ladyhappy.login.presentation

sealed class LoginState {

    class Progress : LoginState()
    class Success : LoginState()
    class Error(val message: String) : LoginState()
}