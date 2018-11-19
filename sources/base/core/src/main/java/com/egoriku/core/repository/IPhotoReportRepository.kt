package com.egoriku.core.repository

import com.egoriku.core.firestore.Result
import com.egoriku.core.model.IPhotoReportModel

interface IPhotoReportRepository {

    suspend fun getPhotoReport(): Result<List<IPhotoReportModel>>
}