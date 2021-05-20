package com.egoriku.ladyhappy.photoreport.domain.usecase

import com.egoriku.ladyhappy.core.dateformat.ddMMMyyyy
import com.egoriku.ladyhappy.core.sharedmodel.mapper.ImageEntityMapper
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.photoreport.data.entity.PhotoReportEntity
import com.egoriku.ladyhappy.photoreport.data.repository.IPhotoReportRepository
import com.egoriku.ladyhappy.photoreport.domain.model.PhotoReportModel

internal class PhotoReportUseCase(
    private val photoReportRepository: IPhotoReportRepository
) : IPhotoReportUseCase {

    private val transformToModel: (PhotoReportEntity) -> PhotoReportModel =
        { entity: PhotoReportEntity ->
            PhotoReportModel(
                date = entity.date.ddMMMyyyy(),
                description = entity.description,
                images = extractPhotos(entity)
            )
        }

    @Deprecated("Remove after implementation MozaikLayout 6+ items")
    private fun extractPhotos(entity: PhotoReportEntity): List<MozaikItem> {
        val list = entity.images.map(ImageEntityMapper())

        return when {
            list.size > 5 -> list.subList(0, 5)
            else -> list
        }
    }

    override suspend fun getPhotoReportInfo() =
        when (val photoReportResult = photoReportRepository.getPhotoReport()) {
            is ResultOf.Failure -> photoReportResult
            is ResultOf.Success -> ResultOf.Success(photoReportResult.value.map(transformToModel))
        }
}

interface IPhotoReportUseCase {

    suspend fun getPhotoReportInfo(): ResultOf<List<PhotoReportModel>>
}
