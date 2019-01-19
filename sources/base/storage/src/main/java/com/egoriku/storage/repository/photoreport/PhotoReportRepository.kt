package com.egoriku.storage.repository.photoreport

import com.egoriku.core.firestore.Result
import com.egoriku.core.model.IPhotoReportModel
import com.egoriku.core.repository.IPhotoReportRepository
import com.egoriku.network.datasource.PhotoReportDataSource

class PhotoReportRepository(private val photoReportDataSource: PhotoReportDataSource)
    : IPhotoReportRepository {

    override suspend fun getPhotoReport(): Result<List<IPhotoReportModel>> {
        val photoReportResult = photoReportDataSource.downloadPhotoReport()

        return when (photoReportResult) {
            is Result.Error -> return photoReportResult
            is Result.Success -> PhotoReportMapper.transform(photoReportResult.value)
        }
    }
}