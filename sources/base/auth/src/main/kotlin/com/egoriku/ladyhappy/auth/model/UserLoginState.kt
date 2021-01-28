package com.egoriku.ladyhappy.auth.model

sealed class UserLoginState {
    object Anon : UserLoginState()

    data class LoggedIn(
            val userId: String,
            val name: String,
            val email: String,
            val photoUrl: String,
            val isEmailVerified: Boolean
    ) : UserLoginState()
}