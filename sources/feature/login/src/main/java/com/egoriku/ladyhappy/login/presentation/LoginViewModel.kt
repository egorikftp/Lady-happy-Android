package com.egoriku.ladyhappy.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.auth.Authentication
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.network.Result.Error
import com.egoriku.network.Result.Success
import kotlinx.coroutines.launch

class LoginViewModel(
        private val authentication: Authentication
) : ViewModel() {

    private val _currentState = MutableLiveData<LoginState>()

    val currentState: LiveData<LoginState> = _currentState

    fun authWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _currentState.value = LoginState.Progress()

            when (val result = authentication.authWithEmailAndPassword(email, password)) {
                is Success -> _currentState.value = LoginState.Success()
                is Error -> _currentState.value = LoginState.Error(result.exception.message
                        ?: EMPTY)
            }
        }
    }
}