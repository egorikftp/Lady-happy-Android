package com.egoriku.giugi.data.entities.mappers

import com.egoriku.giugi.data.entities.CategoriesEntity
import com.egoriku.giugi.data.entities.CategoryEntity
import com.egoriku.giugi.domain.models.CategoriesModel
import com.egoriku.giugi.domain.models.CategoryModel

class CategoriesMapper {

    companion object {
        fun transform(entity: CategoriesEntity): CategoriesModel {
            return CategoriesModel().apply {
                categories = transform(entity.categories)
            }
        }

        private fun transform(categories: HashMap<String, CategoryEntity>): HashMap<String, CategoryModel> {
            val result = hashMapOf<String, CategoryModel>()

            for (entity in categories) {
                result.put(entity.key, CategoryMapper.transform(entity.value))
            }

            return result
        }
    }
}