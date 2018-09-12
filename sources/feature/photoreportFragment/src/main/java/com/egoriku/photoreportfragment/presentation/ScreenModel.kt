package com.egoriku.photoreportfragment.presentation

import com.egoriku.core.model.IPhotoReportModel

data class ScreenModel(
        var photoReports: List<IPhotoReportModel> = listOf()
) {
    fun isPhotoReportsEmpty() = photoReports.isEmpty()
}