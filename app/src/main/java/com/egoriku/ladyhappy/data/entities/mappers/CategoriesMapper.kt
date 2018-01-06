package com.egoriku.ladyhappy.data.entities.mappers

import com.egoriku.ladyhappy.data.entities.CategoriesDocumentEntity
import com.egoriku.ladyhappy.data.entities.CategoryEntity
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import com.egoriku.ladyhappy.domain.models.SingleCategoryModel

class CategoriesMapper {

    companion object {
        fun transform(documentEntity: CategoriesDocumentEntity) = CategoriesModel(
                documentEntity
                        .categories
                        .map {
                            transformToModel(it)
                        }
        )

        private fun transformToModel(it: CategoryEntity): SingleCategoryModel {
            return SingleCategoryModel(
                    id = it.id,
                    key = it.key,
                    imageUrl = it.imageUrl,
                    title = it.title
            )
        }
    }
}