package com.egoriku.photoreportfragment.data.mapper

import com.egoriku.core.firestore.Result
import com.egoriku.ladyhappy.extensions.common.toNewsDate
import com.egoriku.photoreportfragment.data.entity.PhotoReportEntity
import com.egoriku.photoreportfragment.domain.model.PhotoReportModel

object PhotoReportMapper {

    fun transform(entity: List<PhotoReportEntity>) =
            Result.Success(
                    entity.map {
                        transformToModel(it)
                    })


    private fun transformToModel(it: PhotoReportEntity) =
            PhotoReportModel(
                    date = it.date.toNewsDate(),
                    description = it.description ?: "",
                    images = it.images ?: listOf()
            )
}