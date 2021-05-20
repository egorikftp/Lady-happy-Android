package com.egoriku.ladyhappy.detailpage.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams
import com.egoriku.ladyhappy.detailpage.data.entity.DetailEntity
import kotlinx.coroutines.flow.Flow

const val PAGE_SIZE = 10

internal class DetailPaginateRepository(
    private val firebase: IFirebase
) : IDetailPaginateRepository {

    override fun load(params: DetailPageParams) = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            DetailDataSource(firebase = firebase, detailPageParams = params)
        }
    ).flow
}

interface IDetailPaginateRepository {

    fun load(params: DetailPageParams): Flow<PagingData<DetailEntity>>
}