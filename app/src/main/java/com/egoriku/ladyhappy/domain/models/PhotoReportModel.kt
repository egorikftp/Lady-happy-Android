package com.egoriku.ladyhappy.domain.models

import com.egoriku.core.model.IComplexPhotoReportModel
import com.egoriku.core.model.IPhotoReportModel

data class PhotoReportModel(
        override val date: String,
        override val description: String,
        override val images: List<String>
) : IPhotoReportModel

data class ComplexPhotoReportModel(
        override val photoReports: List<IPhotoReportModel>
) : IComplexPhotoReportModel