package com.egoriku.usedLibraries.presentation.state

import com.egoriku.usedLibraries.domain.model.License

sealed class ScreenState {

    data class Success(val licenses: List<License>) : ScreenState()
    object Error : ScreenState()
}