package com.egoriku.ladyhappy.catalog.categories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.categories.domain.usecase.ITabUseCase
import com.egoriku.ladyhappy.extensions.cast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RootCatalogViewModel(private val tabUseCase: ITabUseCase) : ViewModel() {

    private val _tabs = MutableStateFlow<RootScreenModel>(RootScreenModel.Progress)
    val tabs: StateFlow<RootScreenModel> = _tabs

    private val currentTabs: List<TabItem>
        get() = _tabs.value.cast<RootScreenModel.Success>().data

    init {
        viewModelScope.launch {
            _tabs.value = RootScreenModel.Progress
            _tabs.value = tabUseCase.loadTabs()
        }
    }

    fun updateTabs(categoryId: Int) {
        val newTabs = currentTabs.map { tabItem ->
            when (tabItem.categoryId) {
                categoryId -> tabItem.copy(editCount = tabItem.editCount + 1)
                else -> tabItem
            }
        }

        _tabs.value = RootScreenModel.Success(newTabs)
    }
}