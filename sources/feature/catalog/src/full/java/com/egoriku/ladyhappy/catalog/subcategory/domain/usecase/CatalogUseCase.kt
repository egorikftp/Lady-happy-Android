package com.egoriku.ladyhappy.catalog.subcategory.domain.usecase

import com.egoriku.ladyhappy.catalog.subcategory.data.repository.SubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.Image
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogUseCase(private val subcategoryRepository: SubcategoryRepository) {

    suspend fun loadSubCategories(categoryId: Int): Result<List<SubCategoryItem>> =
            when (val subcategories = subcategoryRepository.fetchSubCategories(categoryId)) {
                is Result.Error -> Result.Error(Exception("Response empty"))
                is Result.Success -> Result.Success(
                        withContext(Dispatchers.Default) {
                            subcategories.value
                                    .map {
                                        SubCategoryItem(
                                                headerImage = Image(
                                                        preview = it.images.previewOrEmpty(),
                                                        full = it.images.fullOrEmpty()
                                                ),
                                                name = it.categoryName
                                        )
                                    }
                        }
                )
            }

    private fun Map<String, String>.previewOrEmpty() = this["preview"] ?: EMPTY

    private fun Map<String, String>.fullOrEmpty() = this["full"] ?: EMPTY
}