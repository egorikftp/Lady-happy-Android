package com.egoriku.photoreportfragment.presentation

import com.egoriku.core.model.IPhotoReportModel

class ScreenModel {

    var photoReports: List<IPhotoReportModel>? = null

    var loadState: LoadState = LoadState.NONE

    fun isEmpty() = photoReports.isNullOrEmpty()
}

enum class LoadState {
    NONE,
    PROGRESS,
    ERROR_LOADING,
}