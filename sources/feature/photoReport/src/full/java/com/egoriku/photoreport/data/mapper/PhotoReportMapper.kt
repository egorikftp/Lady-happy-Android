package com.egoriku.photoreport.data.mapper

import com.egoriku.ladyhappy.extensions.common.toNewsDate
import com.egoriku.network.ResultOf
import com.egoriku.photoreport.data.entity.PhotoReportEntity
import com.egoriku.photoreport.domain.model.PhotoReportModel

object PhotoReportMapper {

    fun transform(entity: List<PhotoReportEntity>) =
            ResultOf.Success(
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