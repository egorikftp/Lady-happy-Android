package com.egoriku.storage.data.entity.mapper

import android.support.annotation.Keep
import com.egoriku.core.model.IPhotoReportModel
import com.egoriku.network.data.entities.photoreport.PhotoReportEntity
import com.egoriku.storage.common.DateUtils
import com.egoriku.storage.domain.model.photoreport.ComplexPhotoReportModel
import com.egoriku.storage.domain.model.photoreport.PhotoReportModel

@Keep
class PhotoReportMapper {

    companion object {
        fun transform(news: List<PhotoReportEntity>) = ComplexPhotoReportModel(
                news.map {
                    transformToModel(it)
                }
        )

        private fun transformToModel(it: PhotoReportEntity): IPhotoReportModel {
            return PhotoReportModel(
                    date = DateUtils.instance.convertNewsDateToString(it.date!!) ?: "",
                    description = it.description ?: "",
                    images = it.images ?: listOf()
            )
        }
    }
}