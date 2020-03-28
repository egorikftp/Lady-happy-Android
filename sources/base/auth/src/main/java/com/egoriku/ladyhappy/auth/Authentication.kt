package com.egoriku.ladyhappy.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.network.Result
import com.egoriku.network.firestore.awaitResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Authentication {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _userLoginState = MutableLiveData<UserLoginState>()

    val userLoginState: LiveData<UserLoginState> = _userLoginState

    init {
        invalidateUser()
    }

    private fun invalidateUser() {
        when (val user = auth.currentUser) {
            null -> _userLoginState.postValue(UserLoginState.Anon())
            else -> _userLoginState.postValue(UserLoginState.LoggedIn(
                    userId = user.uid,
                    name = user.displayName ?: EMPTY,
                    email = user.email ?: EMPTY,
                    photoUrl = user.photoUrl?.toString() ?: EMPTY,
                    isEmailVerified = user.isEmailVerified
            ))
        }
    }

    fun logOut() {
        auth.signOut()

        _userLoginState.value = UserLoginState.Anon()
    }

    suspend fun authWithEmailAndPassword(
            email: String,
            password: String
    ): Result<AuthResult> = withContext(Dispatchers.IO) {
        val result: Result<AuthResult> = auth.signInWithEmailAndPassword(email, password).awaitResult()
        invalidateUser()

        result
    }
}