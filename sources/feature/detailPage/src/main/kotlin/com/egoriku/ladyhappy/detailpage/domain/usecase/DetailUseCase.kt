package com.egoriku.ladyhappy.detailpage.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.detailpage.data.IDetailPaginateRepository
import com.egoriku.ladyhappy.detailpage.domain.model.DetailModel
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.network.usecase.FlowUseCase
import com.egoriku.ladyhappy.ui.date.ddMMMyyyy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DetailUseCase(
        private val repository: IDetailPaginateRepository
) : FlowUseCase<DetailPageParams, PagingData<DetailModel>>(Dispatchers.IO), IDetailUseCase {

    override fun execute(parameters: DetailPageParams): Flow<PagingData<DetailModel>> {
        return repository.load(parameters)
                .map { pagingData ->
                    pagingData.map {
                        DetailModel(
                                images = it.images.map { imageEntity ->
                                    MozaikItem(
                                            width = imageEntity.width,
                                            height = imageEntity.height,
                                            url = imageEntity.url
                                    )
                                },
                                description = it.title,
                                date = it.date.ddMMMyyyy()
                        )
                    }
                }
    }
}

interface IDetailUseCase {

    fun execute(parameters: DetailPageParams): Flow<PagingData<DetailModel>>
}