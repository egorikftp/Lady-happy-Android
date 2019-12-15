package com.egoriku.ladyhappy.catalog.root.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.root.domain.usecase.TabUseCase
import kotlinx.coroutines.launch

class RootCatalogViewModel(
        private val tabUseCase: TabUseCase
) : ViewModel() {

    private val _screenModel = MutableLiveData<RootScreenModel>()

    val screenModel: LiveData<RootScreenModel> = _screenModel

    init {
        viewModelScope.launch {
            _screenModel.value = RootScreenModel.Progress()
            _screenModel.value = tabUseCase.loadTabs()
        }
    }
}