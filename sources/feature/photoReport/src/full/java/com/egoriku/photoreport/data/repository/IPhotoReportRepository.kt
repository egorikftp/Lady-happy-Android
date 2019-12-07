package com.egoriku.photoreport.data.repository

import com.egoriku.network.Result
import com.egoriku.photoreport.domain.model.PhotoReportModel

interface IPhotoReportRepository {

    suspend fun getPhotoReport(): Result<List<PhotoReportModel>>
}