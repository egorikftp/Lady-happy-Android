package com.egoriku.ladyhappy.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.extensions.logDm
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Authentication {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _userLoginState = MutableLiveData<UserLoginState>()

    val userLoginState: LiveData<UserLoginState> = _userLoginState

    init {
        when (val user = auth.currentUser) {
            null -> _userLoginState.value = UserLoginState.NotLoggedIn()
            else -> _userLoginState.value = UserLoginState.LoggedIn(
                    userId = user.uid,
                    name = user.displayName ?: EMPTY,
                    email = user.email ?: EMPTY,
                    photoUrl = user.photoUrl?.toString() ?: EMPTY,
                    isEmailVerified = user.isEmailVerified
            )
        }

        /* _userLoginState.value = UserLoginState.LoggedIn(
                 userId = "",
                 name = "Mr Black",
                 email = "egoriktp@gmail.com",
                 photoUrl = "EMPTY",
                 isEmailVerified = true
         )*/
    }

    fun logOut() {
        //auth.signOut()

        _userLoginState.value = UserLoginState.NotLoggedIn()
    }

    fun emailAuthentication(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        logDm("createUserWithEmail:success")
                        val user: FirebaseUser? = auth.currentUser
                        // updateUI(user);
                    } else {
                        logDm("createUserWithEmail:failure ${task.exception?.message}")
                        // updateUI(null);
                    }
                }
    }
}