package com.egoriku.ladyhappy.catalog.subcategory.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.extensions.logDm
import com.egoriku.network.Result.Success
import kotlinx.coroutines.launch

class SubCategoriesViewModel(
        private val catalogUseCase: CatalogUseCase,
        private val categoryId: Int
) : ViewModel() {

    private val _catalogItems = MutableLiveData<SubcategoryScreenState>()

    val subcategoryItems: LiveData<SubcategoryScreenState> = _catalogItems

    init {
        viewModelScope.launch {
            when (val result = catalogUseCase.loadSubCategories(categoryId)) {
                is Success -> {
                    logDm("Success ${result.value}")
                    _catalogItems.value = SubcategoryScreenState.Success(result.value)
                }
                is Error -> {
                    logDm("Error ${result.message}")
                    _catalogItems.value = SubcategoryScreenState.Error
                }
            }
        }
    }
}