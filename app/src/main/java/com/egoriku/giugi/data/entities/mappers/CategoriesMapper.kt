package com.egoriku.giugi.data.entities.mappers

import com.egoriku.giugi.data.entities.CategoriesEntity
import com.egoriku.giugi.domain.models.CategoriesModel

class CategoriesMapper {

    companion object {
        fun transform(entity: CategoriesEntity): CategoriesModel {

            return CategoriesModel()
        }

    }
}