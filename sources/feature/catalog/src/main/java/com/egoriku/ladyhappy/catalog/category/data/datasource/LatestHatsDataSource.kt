package com.egoriku.ladyhappy.catalog.category.data.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.ladyhappy.catalog.category.data.entity.HatEntity

class LatestHatsDataSource(
        private val firebase: IFirebaseFirestore
) {

    fun fetch(hatsType: Int): List<HatEntity> = emptyList()
}