package com.egoriku.ladyhappy.photoreport.presentation

import com.egoriku.ladyhappy.photoreport.domain.model.PhotoReportModel

class ScreenModel(
        val photoReports: List<PhotoReportModel>? = null,
        val loadState: LoadState = LoadState.NONE
) {
    fun isEmpty() = photoReports.isNullOrEmpty()
}

// TODO use sealed classes
enum class LoadState {
    NONE,
    PROGRESS,
    ERROR_LOADING,
}