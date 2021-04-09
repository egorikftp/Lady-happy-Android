package com.egoriku.ladyhappy.photoreport.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.IAnalytics
import com.egoriku.ladyhappy.extensions.logE
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.exception.FirestoreNetworkException
import com.egoriku.ladyhappy.network.exception.FirestoreParseException
import com.egoriku.ladyhappy.network.exception.NoSuchDocumentException
import com.egoriku.ladyhappy.photoreport.domain.usecase.IPhotoReportUseCase
import com.egoriku.ladyhappy.photoreport.presentation.state.PhotoReportUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoReportViewModel(
        private val photoReportUseCase: IPhotoReportUseCase,
        private val analytics: IAnalytics
) : ViewModel() {

    private val _uiState = MutableStateFlow<PhotoReportUiState>(PhotoReportUiState.Loading)
    val uiState: StateFlow<PhotoReportUiState> = _uiState

    init {
        getPhotoReportData()
    }

    private fun getPhotoReportData() {
        viewModelScope.launch {
            _uiState.value = PhotoReportUiState.Loading

            when (val resultOf = photoReportUseCase.getPhotoReportInfo()) {
                is ResultOf.Success -> _uiState.value = PhotoReportUiState.Success(resultOf.value)
                is ResultOf.Failure -> {
                    when (resultOf.throwable) {
                        is FirestoreNetworkException -> {
                            logE("FirestoreNetworkException", resultOf.throwable)
                            analytics.trackNoInternetPhotoReports()
                        }
                        is FirestoreParseException -> logE("FirestoreParseException", resultOf.throwable)
                        is NoSuchDocumentException -> logE("NoSuchDocumentException", resultOf.throwable)
                    }

                    _uiState.value = PhotoReportUiState.Error
                }
            }
        }
    }

    fun retryLoading() = getPhotoReportData()
}