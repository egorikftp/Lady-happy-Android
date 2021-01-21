package com.egoriku.ladyhappy.login.presentation

import android.content.Context
import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.extensions.logD
import com.egoriku.ladyhappy.login.R
import com.egoriku.ladyhappy.login.presentation.state.LoginEvent
import com.egoriku.ladyhappy.login.presentation.state.LoginState
import com.egoriku.ladyhappy.network.ResultOf.Failure
import com.egoriku.ladyhappy.network.ResultOf.Success
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
        context: Context,
        private val authentication: Authentication,
        private val router: IRouter
) : ViewModel() {

    private val googleSignIn = GoogleSignInHelper(context)

    private val _currentState = MutableLiveData<LoginState>()
    private val _events = MutableSharedFlow<LoginEvent>()

    val currentState: LiveData<LoginState> = _currentState
    val events: SharedFlow<LoginEvent> = _events

    fun authWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _currentState.value = LoginState.Progress()

            when (val result = authentication.authWithEmailAndPassword(email, password)) {
                is Success -> _currentState.value = LoginState.Success()
                is Failure -> _currentState.value = LoginState.Error(result.throwable.message
                        ?: EMPTY)
            }
        }
    }

    fun authWithToken(token: String) {
        viewModelScope.launch {
            _currentState.value = LoginState.Progress()

            when (val result = authentication.authWithToken(token)) {
                is Success -> _currentState.value = LoginState.Success()
                is Failure -> _currentState.value = LoginState.Error(result.throwable.message
                        ?: EMPTY)
            }
        }
    }

    fun processBack() = router.back()

    fun trySignIn() = googleSignIn.signIn()

    fun signWithGoogle() = googleSignIn.signWithGoogle()

    fun processOneTapResult(intent: Intent?) {
        runCatching {
            googleSignIn.getCredentials(intent)
        }.onFailure {
            if (it is ApiException) {
                when (it.statusCode) {
                    CommonStatusCodes.CANCELED -> logD("One-tap dialog was closed")
                    CommonStatusCodes.NETWORK_ERROR -> logD("One-tap encountered a network error")
                    else -> logD("Couldn't get credential from result. (${it.localizedMessage})")
                }
            }
        }.onSuccess { credential ->
            val idToken = credential?.googleIdToken

            when {
                idToken != null -> {
                    logD("Got ID token")
                    authWithToken(token = idToken)
                }
                else -> logD("No ID token or password")
            }
        }
    }

    inner class GoogleSignInHelper(context: Context) {

        private val oneTapClient: SignInClient = Identity.getSignInClient(context)

        private val signInWithGoogleClient: GoogleSignInClient = GoogleSignIn.getClient(
                context,
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(context.getString(R.string.server_client_id))
                        .requestEmail()
                        .build()
        )

        private val signInRequest: BeginSignInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(
                        BeginSignInRequest.PasswordRequestOptions.builder()
                                .setSupported(true)
                                .build()
                )
                .setGoogleIdTokenRequestOptions(
                        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                .setSupported(true)
                                .setServerClientId(context.getString(R.string.server_client_id))
                                .setFilterByAuthorizedAccounts(true)
                                .build()
                ).setAutoSelectEnabled(false)
                .build()

        fun signIn() {
            oneTapClient.beginSignIn(signInRequest)
                    .addOnSuccessListener { result ->
                        val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent.intentSender)
                                .setFillInIntent(null)
                                .build()

                        viewModelScope.launch {
                            _events.emit(LoginEvent.OneTap(intentSenderRequest))
                        }
                    }
                    .addOnFailureListener { e ->
                        // No saved credentials found. Launch the One Tap sign-up flow, or
                        // do nothing and continue presenting the signed-out UI.
                        logD(e.message)
                    }
        }

        fun signWithGoogle() {
            viewModelScope.launch {
                _events.emit(LoginEvent.SignWithGoogle(signInWithGoogleClient.signInIntent))
            }
        }

        fun getCredentials(intent: Intent?): SignInCredential? {
            return oneTapClient.getSignInCredentialFromIntent(intent)
        }
    }
}