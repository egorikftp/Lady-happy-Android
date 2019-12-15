package com.egoriku.ladyhappy.catalog.subcategory.domain.usecase

import com.egoriku.ladyhappy.catalog.subcategory.data.repository.LatestHatsRepository
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.SubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.HatModel
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.Image
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogUseCase(
        private val subcategoryRepository: SubcategoryRepository,
        private val latestHatsRepository: LatestHatsRepository
) {

    suspend fun loadSubCategories(documentId: String): Result<List<SubCategoryItem>> =
            when (val subcategories = subcategoryRepository.fetchSubCategories(documentId)) {
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
                                                itemName = it.categoryName,
                                                lastHats = fetchLatestHats(it.hatsType)
                                        )
                                    }
                        }

                )
            }

    private suspend fun fetchLatestHats(hatsType: Int): List<HatModel> =
            when (val result = latestHatsRepository.fetch(hatsType)) {
                is Result.Success -> {
                    withContext(Dispatchers.Default) {
                        result.value
                                .map {
                                    HatModel(
                                            hatsType = it.hatsType,
                                            color = it.color,
                                            images = Image(
                                                    preview = it.images.previewOrEmpty(),
                                                    full = it.images.fullOrEmpty()
                                            ),
                                            createdDate = it.createdDate,
                                            shortDescription = it.shortDescription,
                                            isPopular = it.isPopular
                                    )
                                }
                    }
                }

                is Result.Error -> emptyList()
            }

    private fun Map<String, String>.previewOrEmpty() = this["preview"] ?: EMPTY

    private fun Map<String, String>.fullOrEmpty() = this["full"] ?: EMPTY
}