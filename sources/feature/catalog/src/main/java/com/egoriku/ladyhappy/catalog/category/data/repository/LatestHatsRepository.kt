package com.egoriku.ladyhappy.catalog.category.data.repository

import com.egoriku.ladyhappy.catalog.category.data.datasource.LatestHatsDataSource
import com.egoriku.ladyhappy.catalog.category.data.entity.HatEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LatestHatsRepository(
        private val latestHatsDataSource: LatestHatsDataSource
) {

    suspend fun fetch(hatsType: Int): List<HatEntity> = withContext(Dispatchers.IO) {
        runCatching {
            latestHatsDataSource.fetch(hatsType)
        }.getOrDefault(emptyList())
    }
}