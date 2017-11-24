package com.egoriku.ladyhappy.data.entities.mappers

import com.egoriku.ladyhappy.data.entities.CategoryEntity
import com.egoriku.ladyhappy.domain.models.CategoryModel

class CategoryMapper {

    companion object {
        fun transform(inputModel: CategoryEntity): CategoryModel {
            return CategoryModel().apply {
                id = inputModel.id
                imageUrl = inputModel.imageUrl
                title = inputModel.title
            }
        }
    }
}