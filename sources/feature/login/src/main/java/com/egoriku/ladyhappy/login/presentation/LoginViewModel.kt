package com.egoriku.ladyhappy.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.network.ResultOf.Failure
import com.egoriku.network.ResultOf.Success
import kotlinx.coroutines.launch

class LoginViewModel(
        private val authentication: Authentication,
        private val router: IRouter
) : ViewModel() {

    private val _currentState = MutableLiveData<LoginState>()

    val currentState: LiveData<LoginState> = _currentState

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

    fun processBack() = router.back()
}