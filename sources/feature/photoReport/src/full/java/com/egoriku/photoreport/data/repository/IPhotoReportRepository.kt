package com.egoriku.photoreport.data.repository

import com.egoriku.network.ResultOf
import com.egoriku.photoreport.domain.model.PhotoReportModel

interface IPhotoReportRepository {

    suspend fun getPhotoReport(): ResultOf<List<PhotoReportModel>>
}