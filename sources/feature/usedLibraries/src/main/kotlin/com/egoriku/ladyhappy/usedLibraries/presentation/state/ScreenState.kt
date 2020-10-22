package com.egoriku.ladyhappy.usedLibraries.presentation.state

import com.egoriku.ladyhappy.usedLibraries.domain.model.License

sealed class ScreenState {

    data class Success(val licenses: List<License>) : ScreenState()
    object Error : ScreenState()
}