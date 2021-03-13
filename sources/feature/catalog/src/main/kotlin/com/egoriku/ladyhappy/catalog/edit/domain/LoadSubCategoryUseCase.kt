package com.egoriku.ladyhappy.catalog.edit.domain

import com.egoriku.ladyhappy.catalog.edit.data.IGetSubCategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.network.ResultOf

class LoadSubCategoryUseCase(
        private val getSubCategoryDataSource: IGetSubCategoryDataSource
) : ILoadSubCategoryUseCase {

    override suspend fun load(documentReference: String): SubCategoryItem? {
        return when (val resultOf = getSubCategoryDataSource.load(documentReference = documentReference)) {
            is ResultOf.Success -> {
                val entity = resultOf.value

                SubCategoryItem(
                        categoryId = entity.categoryId,
                        subCategoryId = entity.subCategoryId,
                        subCategoryName = entity.subCategoryName,
                        isPopular = entity.isPopular,
                        images = entity.images.map {
                            MozaikItem(width = it.width, height = it.height, url = it.url)
                        },
                        publishedCount = entity.publishedCount,
                        description = if (entity.description.isEmpty()) "Empty description" else entity.description,
                        documentReference = documentReference
                )
            }
            is ResultOf.Failure -> null
        }
    }
}

interface ILoadSubCategoryUseCase {

    suspend fun load(documentReference: String): SubCategoryItem?
}