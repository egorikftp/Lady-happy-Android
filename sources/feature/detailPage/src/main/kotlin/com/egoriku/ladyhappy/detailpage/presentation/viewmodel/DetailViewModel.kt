package com.egoriku.ladyhappy.detailpage.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.detailpage.domain.model.DetailModel
import com.egoriku.ladyhappy.detailpage.domain.usecase.DetailUseCase
import kotlinx.coroutines.flow.Flow

class DetailViewModel(
        private val detailUseCase: DetailUseCase
) : ViewModel() {

    fun getDetailFlow(detailParams: DetailPageParams): Flow<PagingData<DetailModel>> {
        return detailUseCase
                .invoke(detailParams)
                .cachedIn(viewModelScope)
    }
}