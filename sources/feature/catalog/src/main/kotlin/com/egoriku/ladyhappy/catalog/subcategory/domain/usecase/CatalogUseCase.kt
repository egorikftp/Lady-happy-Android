package com.egoriku.ladyhappy.catalog.subcategory.domain.usecase

import com.egoriku.ladyhappy.catalog.subcategory.data.entity.SubCategoryEntity
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.ISubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.core.sharedmodel.ImageEntity
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.network.ResultOf

internal class CatalogUseCase(
        private val subcategoryRepository: ISubcategoryRepository
) : ICatalogUseCase {

    private val entityTransform: (SubCategoryEntity) -> SubCategoryItem = { entity: SubCategoryEntity ->
        SubCategoryItem(
                categoryId = entity.categoryId,
                subCategoryId = entity.subCategoryId,
                images = entity.images.map(imageTransform),
                name = entity.subCategoryName,
                isPopular = entity.isPopular,
                publishedCount = entity.publishedCount,
                description = entity.description
        )
    }

    private val imageTransform: (ImageEntity) -> MozaikItem = { image: ImageEntity ->
        MozaikItem(
                width = image.width,
                height = image.height,
                url = image.url
        )
    }

    override suspend fun loadSubCategories(categoryId: Int) =
            when (val subcategories = subcategoryRepository.fetchSubCategories(categoryId)) {
                is ResultOf.Failure -> ResultOf.Failure(Exception("Response empty"))
                is ResultOf.Success -> ResultOf.Success(
                        subcategories.value
                                .filterNot { it.images.isEmpty() }
                                .map(entityTransform)
                )
            }
}

interface ICatalogUseCase {

    suspend fun loadSubCategories(categoryId: Int): ResultOf<List<SubCategoryItem>>
}