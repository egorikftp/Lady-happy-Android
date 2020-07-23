package com.egoriku.ladyhappy.catalog.categories.domain.usecase

import com.egoriku.ladyhappy.catalog.categories.data.entity.TabEntity
import com.egoriku.ladyhappy.catalog.categories.data.repository.TabRepository
import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.categories.presentation.RootScreenModel
import com.egoriku.network.ResultOf.Failure
import com.egoriku.network.ResultOf.Success

class TabUseCase(private val tabRepository: TabRepository) {

    suspend fun loadTabs(): RootScreenModel = when (val result = tabRepository.load()) {
        is Success -> {
            val list = result.value

            when {
                list.isEmpty() -> RootScreenModel.Error()
                else -> RootScreenModel.Success(list.mapNotNull { it.mapOrNull() })
            }
        }
        is Failure -> RootScreenModel.Error()
    }

    private fun TabEntity.mapOrNull() = if (id != null && name != null) {
        TabItem(
                categoryId = id,
                categoryName = name
        )
    } else null
}