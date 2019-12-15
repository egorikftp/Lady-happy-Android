package com.egoriku.ladyhappy.catalog.subcategory.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.network.Result.Success
import kotlinx.coroutines.launch

class SubCategoriesViewModel(
        private val catalogUseCase: CatalogUseCase,
        private val documentId: String
) : ViewModel() {

    private val _catalogItems = MutableLiveData<SubcategoryScreenState>()

    val subcategoryItems: LiveData<SubcategoryScreenState> = _catalogItems

    init {
        viewModelScope.launch {
            when (val result = catalogUseCase.loadSubCategories(documentId)) {
                is Success -> _catalogItems.value = SubcategoryScreenState.Success(result.value)
                is Error -> _catalogItems.value = SubcategoryScreenState.Error()
            }
        }
    }
}