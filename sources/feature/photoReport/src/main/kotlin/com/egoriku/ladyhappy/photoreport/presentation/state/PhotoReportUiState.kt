package com.egoriku.ladyhappy.photoreport.presentation.state

import com.egoriku.ladyhappy.photoreport.domain.model.PhotoReportModel

sealed class PhotoReportUiState {
    object Loading : PhotoReportUiState()

    object Error : PhotoReportUiState()

    data class Success(
        val photoReports: List<PhotoReportModel>
    ) : PhotoReportUiState()
}