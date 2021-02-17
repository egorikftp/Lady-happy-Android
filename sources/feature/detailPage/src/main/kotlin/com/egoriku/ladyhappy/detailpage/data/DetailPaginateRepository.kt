package com.egoriku.ladyhappy.detailpage.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.sharedmodel.params.DetailPageParams

const val PAGE_SIZE = 10

class DetailPaginateRepository(private val firebase: IFirebase) {

    fun load(params: DetailPageParams) = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                DetailDataSource(firebase = firebase, detailPageParams = params)
            }
    ).flow
}