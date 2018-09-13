package com.egoriku.storage.domain.model.photoreport

import com.egoriku.core.model.IComplexPhotoReportModel
import com.egoriku.core.model.IPhotoReportModel

data class ComplexPhotoReportModel(
        override val photoReports: List<IPhotoReportModel>
) : IComplexPhotoReportModel