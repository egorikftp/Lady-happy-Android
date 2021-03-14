package com.egoriku.ladyhappy.edit.data.mapper

import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.core.sharedmodel.entity.SubCategoryEntity
import com.egoriku.ladyhappy.core.sharedmodel.mapper.ImageEntityMapper

class SubCategoryEntityMapper : (SubCategoryEntity) -> SubCategoryModel? {

    //todo Pass localized string
    override fun invoke(entity: SubCategoryEntity) = SubCategoryModel(
            categoryId = entity.categoryId,
            subCategoryId = entity.subCategoryId,
            subCategoryName = entity.subCategoryName,
            isPopular = entity.isPopular,
            images = entity.images.map(ImageEntityMapper()),
            publishedCount = entity.publishedCount,
            description = when {
                entity.description.isEmpty() -> "Empty description"
                else -> entity.description
            },
            documentReference = entity.documentReference
    )
}