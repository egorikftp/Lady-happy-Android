package com.egoriku.photoreportfragment.data.repository

import com.egoriku.network.firestore.Result
import com.egoriku.photoreportfragment.domain.model.PhotoReportModel

interface IPhotoReportRepository {

    suspend fun getPhotoReport(): Result<List<PhotoReportModel>>
}