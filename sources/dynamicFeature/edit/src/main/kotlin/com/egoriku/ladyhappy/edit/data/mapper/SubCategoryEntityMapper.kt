package com.egoriku.ladyhappy.edit.data.mapper

import com.egoriku.ladyhappy.core.IStringResource
import com.egoriku.ladyhappy.core.dateformat.ddMMMyyyyHHmm
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.core.sharedmodel.entity.SubCategoryEntity
import com.egoriku.ladyhappy.core.sharedmodel.mapper.ImageEntityMapper

class SubCategoryEntityMapper(
        private val stringResource: IStringResource
) : (SubCategoryEntity) -> SubCategoryModel? {

    override fun invoke(entity: SubCategoryEntity) = SubCategoryModel(
            categoryId = entity.categoryId,
            subCategoryId = entity.subCategoryId,
            subCategoryName = entity.subCategoryName,
            isPopular = entity.isPopular,
            images = entity.images.map(ImageEntityMapper()),
            publishedCount = entity.publishedCount,
            description = when {
                entity.description.isEmpty() -> stringResource.emptyDescription
                else -> entity.description
            },
            documentReference = entity.documentReference,
            lastEditTime = when (val date = entity.lastEditTime) {
                null -> stringResource.notEdited
                else -> date.ddMMMyyyyHHmm()
            }
    )
}