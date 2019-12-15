package com.egoriku.ladyhappy.catalog.subcategory.data.repository

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.LatestHatsDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.entity.HatEntity
import com.egoriku.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LatestHatsRepository(
        private val latestHatsDataSource: LatestHatsDataSource
) {

    suspend fun fetch(hatsType: Int): Result<List<HatEntity>> = withContext(Dispatchers.IO) {
        runCatching {
            Result.Success(latestHatsDataSource.fetch(hatsType))
        }.getOrElse {
            Result.Error(it)
        }
    }
}