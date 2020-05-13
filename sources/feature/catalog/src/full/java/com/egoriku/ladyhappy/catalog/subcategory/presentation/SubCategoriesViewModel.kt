package com.egoriku.ladyhappy.catalog.subcategory.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.network.Result

class SubCategoriesViewModel(
        private val catalogUseCase: CatalogUseCase,
        private val categoryId: Int
) : ViewModel() {

    val subcategoryItems: LiveData<SubcategoryScreenState> = liveData {
        when (val result = catalogUseCase.loadSubCategories(categoryId)) {
            is Result.Success -> emit(SubcategoryScreenState.Success(result.value))
            is Result.Error -> emit(SubcategoryScreenState.Error)
        }
    }
}