package com.egoriku.ladyhappy.data.entities.mappers

import android.support.annotation.Keep
import com.egoriku.ladyhappy.common.utils.DateUtils
import com.egoriku.ladyhappy.data.entities.SingleNewsEntity
import com.egoriku.ladyhappy.domain.models.NewsModel
import com.egoriku.ladyhappy.domain.models.SingleNewsModel

@Keep
class NewsMapper {

    companion object {
        fun transform(news: List<SingleNewsEntity>) = NewsModel(
                news.map {
                    transformToModel(it)
                }
        )

        private fun transformToModel(it: SingleNewsEntity): SingleNewsModel {
            return SingleNewsModel(
                    date = DateUtils.instance.convertNewsDateToString(it.date),
                    description = it.description,
                    images = it.images
            )
        }
    }
}