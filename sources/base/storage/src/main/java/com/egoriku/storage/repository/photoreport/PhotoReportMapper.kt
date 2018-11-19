package com.egoriku.storage.repository.photoreport

import com.egoriku.core.firestore.Result
import com.egoriku.core.model.IPhotoReportModel
import com.egoriku.network.data.entities.photoreport.PhotoReportEntity
import com.egoriku.storage.common.DateUtils
import com.egoriku.storage.domain.model.photoreport.PhotoReportModel

object PhotoReportMapper {

    fun transform(entity: List<PhotoReportEntity>) =
            Result.Success(
                    entity.map {
                        transformToModel(it)
                    })


    private fun transformToModel(it: PhotoReportEntity): IPhotoReportModel {
        return PhotoReportModel(
                date = DateUtils.instance.convertNewsDateToString(it.date!!),
                description = it.description ?: "",
                images = it.images ?: listOf()
        )
    }
}