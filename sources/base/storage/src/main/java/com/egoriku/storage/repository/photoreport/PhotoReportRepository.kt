package com.egoriku.storage.repository.photoreport

import com.egoriku.core.model.IComplexPhotoReportModel
import com.egoriku.core.repository.IPhotoReportRepository
import com.egoriku.network.datasource.PhotoReportDataSource
import com.egoriku.storage.data.entity.mapper.PhotoReportMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoReportRepository
@Inject constructor(private val photoReportDataSourceRemote: PhotoReportDataSource)
    : IPhotoReportRepository {

    override fun getPhotoReportInfo(): Observable<IComplexPhotoReportModel> =
            photoReportDataSourceRemote
                    .downloadPhotoReport()
                    .map { news -> PhotoReportMapper.transform(news) }
}