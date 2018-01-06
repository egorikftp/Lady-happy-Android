package com.egoriku.ladyhappy.data.entities.mappers

import com.egoriku.ladyhappy.data.entities.NewsDocumentEntity
import com.egoriku.ladyhappy.data.entities.NewsEntity
import com.egoriku.ladyhappy.domain.models.NewsModel
import com.egoriku.ladyhappy.domain.models.SingleNewsModel
import com.egoriku.ladyhappy.domain.models.SingleCategoryModel

class NewsMapper {

    companion object {
        fun transform(documentEntity: NewsDocumentEntity) = NewsModel(
                documentEntity
                        .news
                        .map {
                            transformToModel(it)
                        }
        )

        private fun transformToModel(it: NewsEntity): SingleNewsModel {
            return SingleNewsModel(
                    date = it.date,
                    description = it.description,
                    images = it.images
            )
        }
    }
}