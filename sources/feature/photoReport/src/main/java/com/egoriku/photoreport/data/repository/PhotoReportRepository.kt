package com.egoriku.photoreport.data.repository

import com.egoriku.network.firestore.Result
import com.egoriku.photoreport.data.repository.datasource.PhotoReportDataSource
import com.egoriku.photoreport.data.mapper.PhotoReportMapper
import com.egoriku.photoreport.domain.model.PhotoReportModel

class PhotoReportRepository(private val photoReportDataSource: PhotoReportDataSource)
    : IPhotoReportRepository {

    override suspend fun getPhotoReport(): Result<List<PhotoReportModel>> {
        val photoReportResult = photoReportDataSource.downloadPhotoReport()

        return when (photoReportResult) {
            is Result.Error -> return photoReportResult
            is Result.Success -> PhotoReportMapper.transform(photoReportResult.value)
        }
    }
}