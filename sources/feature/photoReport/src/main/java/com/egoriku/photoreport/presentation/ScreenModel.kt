package com.egoriku.photoreport.presentation

import com.egoriku.photoreport.domain.model.PhotoReportModel

class ScreenModel {

    var photoReports: List<PhotoReportModel>? = null

    var loadState: LoadState = LoadState.NONE

    fun isEmpty() = photoReports.isNullOrEmpty()
}

enum class LoadState {
    NONE,
    PROGRESS,
    ERROR_LOADING,
}