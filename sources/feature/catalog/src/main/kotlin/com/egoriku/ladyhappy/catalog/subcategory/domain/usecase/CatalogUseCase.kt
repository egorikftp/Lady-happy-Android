package com.egoriku.ladyhappy.catalog.subcategory.domain.usecase

import com.egoriku.ladyhappy.catalog.subcategory.data.repository.ISubcategoryRepository
import com.egoriku.ladyhappy.core.IStringResource
import com.egoriku.ladyhappy.core.dateformat.ddMMMyyyy
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.core.sharedmodel.entity.SubCategoryEntity
import com.egoriku.ladyhappy.core.sharedmodel.mapper.ImageEntityMapper
import com.egoriku.ladyhappy.network.ResultOf

internal class CatalogUseCase(
        private val subcategoryRepository: ISubcategoryRepository,
        private val stringResource: IStringResource
) : ICatalogUseCase {

    private val entityTransform: (SubCategoryEntity) -> SubCategoryModel = { entity: SubCategoryEntity ->
        SubCategoryModel(
                categoryId = entity.categoryId,
                subCategoryId = entity.subCategoryId,
                images = entity.images.map(ImageEntityMapper()),
                subCategoryName = entity.subCategoryName,
                isPopular = entity.isPopular,
                publishedCount = entity.publishedCount,
                description = entity.description,
                documentReference = entity.documentReference,
                lastEditTime  = when (val date = entity.lastEditTime) {
                    null -> stringResource.notEdited
                    else -> date.ddMMMyyyy()
                }
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

    suspend fun loadSubCategories(categoryId: Int): ResultOf<List<SubCategoryModel>>
}