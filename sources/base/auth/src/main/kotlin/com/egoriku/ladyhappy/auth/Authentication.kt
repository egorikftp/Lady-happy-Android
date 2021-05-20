package com.egoriku.ladyhappy.auth

import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class Authentication {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _userLoginState = MutableStateFlow<UserLoginState>(UserLoginState.Anon)
    val userLoginState: StateFlow<UserLoginState> = _userLoginState

    init {
        invalidateUser()
    }

    private fun invalidateUser() {
        when (val user = auth.currentUser) {
            null -> _userLoginState.value = UserLoginState.Anon
            else -> _userLoginState.value = UserLoginState.LoggedIn(
                userId = user.uid,
                name = user.displayName ?: EMPTY,
                email = user.email ?: EMPTY,
                photoUrl = user.photoUrl?.toString() ?: EMPTY,
                isEmailVerified = user.isEmailVerified
            )
        }
    }

    fun logOut() {
        auth.signOut()

        _userLoginState.value = UserLoginState.Anon
    }

    suspend fun authWithEmailAndPassword(
        email: String,
        password: String,
    ): ResultOf<AuthResult> = withContext(Dispatchers.IO) {
        val resultOf: ResultOf<AuthResult> =
            auth.signInWithEmailAndPassword(email, password).awaitResult()

        withContext(Dispatchers.Main) {
            invalidateUser()
        }

        resultOf
    }

    suspend fun authWithToken(tokenId: String): ResultOf<AuthResult> = withContext(Dispatchers.IO) {
        val credential = GoogleAuthProvider.getCredential(tokenId, null)

        val resultOf: ResultOf<AuthResult> = auth.signInWithCredential(credential).awaitResult()

        withContext(Dispatchers.Main) {
            invalidateUser()
        }

        resultOf
    }
}