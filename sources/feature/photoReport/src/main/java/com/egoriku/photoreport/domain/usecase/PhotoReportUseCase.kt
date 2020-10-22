package com.egoriku.photoreport.domain.usecase

import com.egoriku.extensions.common.Constants.EMPTY
import com.egoriku.extensions.common.toNewsDate
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.photoreport.data.entity.PhotoReportEntity
import com.egoriku.photoreport.data.repository.PhotoReportRepository
import com.egoriku.photoreport.domain.model.PhotoReportModel

class PhotoReportUseCase(private val photoReportRepository: PhotoReportRepository) {

    private val transformToModel: (PhotoReportEntity) -> PhotoReportModel = { entity: PhotoReportEntity ->
        PhotoReportModel(
                date = entity.date.toNewsDate(),
                description = entity.description ?: EMPTY,
                images = entity.images ?: emptyList()
        )
    }

    suspend fun getPhotoReportInfo(): ResultOf<List<PhotoReportModel>> {
        return when (val photoReportResult = photoReportRepository.getPhotoReport()) {
            is ResultOf.Failure -> return photoReportResult
            is ResultOf.Success ->
                ResultOf.Success(photoReportResult.value.map(transformToModel))
        }
    }
}