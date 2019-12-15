package com.egoriku.photoreport.presentation

import com.egoriku.photoreport.domain.model.PhotoReportModel

class ScreenModel(
        val photoReports: List<PhotoReportModel>? = null,
        val loadState: LoadState = LoadState.NONE
) {
    fun isEmpty() = photoReports.isNullOrEmpty()
}

//todo use sealed classes
enum class LoadState {
    NONE,
    PROGRESS,
    ERROR_LOADING,
}