package com.egoriku.giugi.data.entities.mappers

import com.egoriku.giugi.data.entities.CategoryEntity
import com.egoriku.giugi.domain.models.CategoryModel

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