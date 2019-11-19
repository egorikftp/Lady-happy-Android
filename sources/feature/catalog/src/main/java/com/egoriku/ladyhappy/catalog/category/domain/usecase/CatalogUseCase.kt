package com.egoriku.ladyhappy.catalog.category.domain.usecase

import com.egoriku.ladyhappy.catalog.category.data.repository.CategoriesRepository
import com.egoriku.ladyhappy.catalog.category.data.repository.LatestHatsRepository
import com.egoriku.ladyhappy.catalog.category.domain.model.CatalogItem
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.network.Result

class CatalogUseCase(
        private val categoriesRepository: CategoriesRepository,
        private val latestHatsRepository: LatestHatsRepository
) {

    suspend fun loadCategories(): Result<List<CatalogItem>> {
        val categories = categoriesRepository.fetchCategories()

        return if (categories.isEmpty()) {
            Result.Error(Exception("Response empty"))
        } else {
            Result.Success(
                    categories
                            .map {
                                CatalogItem(
                                        itemLogoUrl = it.imageUrl,
                                        itemName = it.categoryName,
                                        lastHatsImageUrl = fetchLatestHats(it.hatsType)
                                )
                            }
            )
        }
    }

    private suspend fun fetchLatestHats(hatsType: Int): List<String> =
            latestHatsRepository.fetch(hatsType)
                    .map { it.imageUrl ?: EMPTY }
}