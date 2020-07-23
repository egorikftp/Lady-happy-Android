package com.egoriku.ladyhappy.catalog.subcategory.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.network.ResultOf

class SubCategoriesViewModel(
        private val catalogUseCase: CatalogUseCase,
        private val categoryId: Int
) : ViewModel() {

    val subcategoryItems: LiveData<SubcategoryScreenState> = liveData {
        emit(SubcategoryScreenState.Loading)

        when (val result = catalogUseCase.loadSubCategories(categoryId)) {
            is ResultOf.Success -> emit(SubcategoryScreenState.Success(result.value))
            is ResultOf.Failure -> emit(SubcategoryScreenState.Error)
        }
    }
}