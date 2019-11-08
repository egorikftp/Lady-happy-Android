package com.egoriku.ladyhappy.catalog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.catalog.presentation.stub.screenData
import com.egoriku.network.Result.Success
import kotlinx.coroutines.launch

class CatalogListViewModel(
        private val catalogUseCase: CatalogUseCase
) : ViewModel() {

    private val _catalogItems = MutableLiveData<CatalogScreenState>()

    val catalogItems: LiveData<CatalogScreenState> = _catalogItems

    init {
        _catalogItems.value = CatalogScreenState.Success(
                screenData = screenData
        )

        viewModelScope.launch {
            when (val result = catalogUseCase.loadCategories()) {

                is Success -> _catalogItems.value = CatalogScreenState.Success(result.value)
                is Error -> _catalogItems.value = CatalogScreenState.Error()
            }
        }
    }
}