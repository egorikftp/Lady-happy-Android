package com.egoriku.ladyhappy.catalog.root.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RootCatalogViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<String>>()

    val categories: LiveData<List<String>> = _categories

    init {
        viewModelScope.launch {
            delay(4000)

            _categories.value = listOf(
                    "Фетр",
                    "Синамей",
                    "Кринолин",
                    "Вуалетки",
                    "Войлок",
                    "Реставрация"
            )
        }
    }
}