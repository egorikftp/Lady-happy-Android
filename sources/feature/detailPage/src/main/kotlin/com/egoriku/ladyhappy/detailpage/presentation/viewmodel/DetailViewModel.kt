package com.egoriku.ladyhappy.detailpage.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.detailpage.domain.model.DetailModel
import com.egoriku.ladyhappy.detailpage.domain.usecase.IDetailUseCase
import com.egoriku.ladyhappy.detailpage.presentation.state.FilterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(
        private val detailUseCase: IDetailUseCase
) : ViewModel() {

    private val _filterState = MutableStateFlow(FilterState.None)
    val filterState: StateFlow<FilterState> = _filterState

    private var currentParams: DetailPageParams? = null
    private var detailUseCaseResult: Flow<PagingData<DetailModel>>? = null

    fun getDetailFlow(detailParams: DetailPageParams): Flow<PagingData<DetailModel>> {
        val flow = detailUseCaseResult

        if (detailParams == currentParams && flow != null) {
            return flow
        }

        val newDetailUseCaseResult = detailUseCase
                .execute(detailParams)
                .cachedIn(viewModelScope)

        currentParams = detailParams
        detailUseCaseResult = newDetailUseCaseResult

        return newDetailUseCaseResult
    }
}