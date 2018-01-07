package com.egoriku.ladyhappy.data.entities.mappers

import com.egoriku.ladyhappy.common.utils.DateUtils
import com.egoriku.ladyhappy.data.entities.NewsEntity
import com.egoriku.ladyhappy.data.entities.SingleNewsEntity
import com.egoriku.ladyhappy.domain.models.NewsModel
import com.egoriku.ladyhappy.domain.models.SingleNewsModel

class NewsMapper {

    companion object {
        fun transform(newsEntity: NewsEntity) = NewsModel(
                newsEntity
                        .news
                        .map {
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