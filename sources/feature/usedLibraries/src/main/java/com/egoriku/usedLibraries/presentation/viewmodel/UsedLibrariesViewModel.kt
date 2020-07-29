package com.egoriku.usedLibraries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egoriku.network.ResultOf
import com.egoriku.usedLibraries.domain.usecase.LicenseUseCase
import com.egoriku.usedLibraries.presentation.state.ScreenState

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