package com.egoriku.ladyhappy.settings.domain.usecase

import androidx.lifecycle.asFlow
import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.settings.domain.model.Section
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class AuthenticationUseCase(
        private val authentication: Authentication
) {

    suspend fun subscribeAuthEvents() = flow {
        authentication.userLoginState.asFlow().collect {
            emit(Section.Login(it))
        }
    }

    fun logout() = authentication.logOut()
}