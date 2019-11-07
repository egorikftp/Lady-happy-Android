package com.egoriku.ladyhappy.catalog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.ladyhappy.catalog.presentation.stub.screenData

class CatalogListViewModel : ViewModel() {

    private val _catalogItems = MutableLiveData<CatalogScreenState>()

    val catalogItems: LiveData<CatalogScreenState> = _catalogItems

    init {
        _catalogItems.value = CatalogScreenState.Success(
                screenData = screenData
        )
    }
}