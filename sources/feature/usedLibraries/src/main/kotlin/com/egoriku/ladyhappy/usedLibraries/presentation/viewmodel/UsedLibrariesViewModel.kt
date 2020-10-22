package com.egoriku.ladyhappy.usedLibraries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.usedLibraries.domain.usecase.LicenseUseCase
import com.egoriku.ladyhappy.usedLibraries.presentation.state.ScreenState

class UsedLibrariesViewModel(
        private val licenseUseCase: LicenseUseCase
) : ViewModel() {

    val licenses: LiveData<ScreenState> = liveData {
        when (val result = licenseUseCase(Unit)) {
            is ResultOf.Success -> emit(ScreenState.Success(result.value))
            is ResultOf.Failure -> emit(ScreenState.Error)
        }
    }
}