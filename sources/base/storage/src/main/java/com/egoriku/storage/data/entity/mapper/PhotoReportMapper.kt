package com.egoriku.storage.data.entity.mapper

import android.support.annotation.Keep
import com.egoriku.ladyhappy.common.utils.DateUtils
import com.egoriku.ladyhappy.domain.models.ComplexPhotoReportModel
import com.egoriku.ladyhappy.domain.models.PhotoReportModel
import com.egoriku.network.data.entities.photoreport.PhotoReportEntity

@Keep
class PhotoReportMapper {

    companion object {
        fun transform(news: List<PhotoReportEntity>) = ComplexPhotoReportModel(
                news.map {
                    transformToModel(it)
                }
        )

        private fun transformToModel(it: PhotoReportEntity): PhotoReportModel {
            return PhotoReportModel(
                    date = DateUtils.instance.convertNewsDateToString(it.date!!) ?: "",
                    description = it.description ?: "",
                    images = it.images ?: listOf()
            )
        }
    }
}