package com.egoriku.storage.domain.model.photoreport

import com.egoriku.core.model.IPhotoReportModel

data class PhotoReportModel(
        override val date: String,
        override val description: String,
        override val images: List<String>
) : IPhotoReportModel