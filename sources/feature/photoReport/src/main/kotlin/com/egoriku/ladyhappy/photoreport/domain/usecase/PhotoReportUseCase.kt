package com.egoriku.ladyhappy.photoreport.domain.usecase

import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.photoreport.data.entity.PhotoReportEntity
import com.egoriku.ladyhappy.photoreport.data.repository.PhotoReportRepository
import com.egoriku.ladyhappy.photoreport.domain.model.PhotoReportModel
import com.egoriku.ladyhappy.ui.date.ddMMMyyyy

class PhotoReportUseCase(private val photoReportRepository: PhotoReportRepository) {

    private val transformToModel: (PhotoReportEntity) -> PhotoReportModel = { entity: PhotoReportEntity ->
        PhotoReportModel(
                date = entity.date.ddMMMyyyy(),
                description = entity.description,
                images = extractPhotos(entity)
        )
    }

    @Deprecated("Remove after implementation MozaikLayout 6+ items")
    private fun extractPhotos(entity: PhotoReportEntity): List<MozaikItem> {
        val list = entity.images.map {
            MozaikItem(
                    width = it.width,
                    height = it.height,
                    url = it.url
            )
        }

        return when {
            list.size > 5 -> list.subList(0, 5)
            else -> list
        }
    }

    suspend fun getPhotoReportInfo(): ResultOf<List<PhotoReportModel>> {
        return when (val photoReportResult = photoReportRepository.getPhotoReport()) {
            is ResultOf.Failure -> return photoReportResult
            is ResultOf.Success ->
                ResultOf.Success(photoReportResult.value.map(transformToModel))
        }
    }
}