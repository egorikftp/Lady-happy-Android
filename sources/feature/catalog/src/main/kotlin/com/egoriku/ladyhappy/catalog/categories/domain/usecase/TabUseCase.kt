package com.egoriku.ladyhappy.catalog.categories.domain.usecase

import com.egoriku.ladyhappy.catalog.categories.data.entity.TabEntity
import com.egoriku.ladyhappy.catalog.categories.data.repository.ITabRepository
import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.categories.presentation.RootScreenModel
import com.egoriku.ladyhappy.network.ResultOf.Failure
import com.egoriku.ladyhappy.network.ResultOf.Success

internal class TabUseCase(
    private val tabRepository: ITabRepository
) : ITabUseCase {

    override suspend fun loadTabs(): RootScreenModel = when (val result = tabRepository.load()) {
        is Success -> {
            val list = result.value

            when {
                list.isEmpty() -> RootScreenModel.Error
                else -> RootScreenModel.Success(list.mapNotNull { it.mapOrNull() })
            }
        }
        is Failure -> RootScreenModel.Error
    }

    private fun TabEntity.mapOrNull() = if (id != -1 && name.isNotEmpty()) {
        TabItem(
            categoryId = id,
            categoryName = name
        )
    } else null
}

interface ITabUseCase {

    suspend fun loadTabs(): RootScreenModel
}