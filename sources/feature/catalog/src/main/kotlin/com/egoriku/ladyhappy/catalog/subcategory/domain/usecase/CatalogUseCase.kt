package com.egoriku.ladyhappy.catalog.subcategory.domain.usecase

import com.egoriku.ladyhappy.catalog.subcategory.data.entity.Image
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.SubCategoryEntity
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.SubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.network.ResultOf

class CatalogUseCase(private val subcategoryRepository: SubcategoryRepository) {

    private val entityTransform: (SubCategoryEntity) -> SubCategoryItem = { entity: SubCategoryEntity ->
        SubCategoryItem(
                images = entity.images.map(imageTransform),
                name = entity.subCategoryName,
                isPopular = entity.isPopular,
                publishedCount = entity.publishedCount
        )
    }

    private val imageTransform: (Image) -> MozaikItem = { image: Image ->
        MozaikItem(
                width = image.width,
                height = image.height,
                url = image.url
        )
    }

    suspend fun loadSubCategories(categoryId: Int): ResultOf<List<SubCategoryItem>> =
            when (val subcategories = subcategoryRepository.fetchSubCategories(categoryId)) {
                is ResultOf.Failure -> ResultOf.Failure(Exception("Response empty"))
                is ResultOf.Success -> ResultOf.Success(
                        subcategories.value
                                .filterNot { it.images.isEmpty() }
                                .map(entityTransform)
                )
            }
}