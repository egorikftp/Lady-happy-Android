package com.egoriku.photoreport.data.repository

import com.egoriku.network.ResultOf
import com.egoriku.photoreport.data.mapper.PhotoReportMapper
import com.egoriku.photoreport.data.repository.datasource.PhotoReportDataSource
import com.egoriku.photoreport.domain.model.PhotoReportModel

class PhotoReportRepository(private val photoReportDataSource: PhotoReportDataSource)
    : IPhotoReportRepository {

    override suspend fun getPhotoReport(): ResultOf<List<PhotoReportModel>> {
        val photoReportResult = photoReportDataSource.downloadPhotoReport()

        return when (photoReportResult) {
            is ResultOf.Failure -> return photoReportResult
            is ResultOf.Success -> PhotoReportMapper.transform(photoReportResult.value)
        }
    }
}