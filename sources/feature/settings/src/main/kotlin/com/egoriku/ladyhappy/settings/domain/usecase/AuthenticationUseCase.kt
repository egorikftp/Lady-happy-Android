package com.egoriku.ladyhappy.settings.domain.usecase

import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.settings.domain.model.Section
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

internal class AuthenticationUseCase(
    private val authentication: Authentication
) : IAuthenticationUseCase {

    override suspend fun subscribeAuthEvents() = flow {
        authentication.userLoginState.collect {
            emit(Section.Login(it))
        }
    }

    override fun logout() = authentication.logOut()
}

interface IAuthenticationUseCase {

    suspend fun subscribeAuthEvents(): Flow<Section.Login>

    fun logout()
}