package com.egoriku.ladyhappy.detailpage.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.detailpage.data.DetailPaginateRepository
import com.egoriku.ladyhappy.detailpage.domain.model.DetailModel
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.network.usecase.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailUseCase(
        private val repository: DetailPaginateRepository
) : FlowUseCase<DetailPageParams, PagingData<DetailModel>>(Dispatchers.IO) {

    override fun execute(parameters: DetailPageParams): Flow<PagingData<DetailModel>> {
        return repository.load()
                .map { pagingData ->
                    pagingData.map {
                        DetailModel(
                                images = listOf(
                                        MozaikItem(1200, 800, "https://firebasestorage.googleapis.com/v0/b/lady-happy.appspot.com/o/subcategories%2F1.2%2FIMG_0207_2018.09.06_22-49%20(1).jpg?alt=media&token=b29b83cb-f2e8-4512-bd8a-b5cc120bd179"),
                                        MozaikItem(1200, 800, "https://firebasestorage.googleapis.com/v0/b/lady-happy.appspot.com/o/subcategories%2F1.2%2FIMG_0207_2018.09.06_22-49%20(1).jpg?alt=media&token=b29b83cb-f2e8-4512-bd8a-b5cc120bd179")
                                ),
                                description = it.id.toString(),
                                date = it.id.toString()
                        )
                    }
                }
    }
}