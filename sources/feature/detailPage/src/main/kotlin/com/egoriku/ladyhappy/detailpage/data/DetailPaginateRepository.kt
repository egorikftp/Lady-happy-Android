package com.egoriku.ladyhappy.detailpage.data

import androidx.paging.Pager
import androidx.paging.PagingConfig

const val PAGE_SIZE = 4

class DetailPaginateRepository(private val dataSource: DetailDataSource) {

    fun load() = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { dataSource }
    ).flow
}