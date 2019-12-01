package com.egoriku.photoreport.data.repository.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.network.firestore.awaitResult
import com.egoriku.photoreport.data.entity.PhotoReportEntity
import com.google.firebase.firestore.Query
import javax.inject.Inject

class PhotoReportDataSource
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore) {

    companion object {
        const val COLLECTION_KEY_NEWS = "news"
        const val QUERY_DATE = "date"
    }

    suspend fun downloadPhotoReport() = getPhotoReportQuery().awaitResult<PhotoReportEntity>()

    private fun getPhotoReportQuery(): Query = firebaseFirestore
            .getFirebase()
            .collection(COLLECTION_KEY_NEWS)
            .orderBy(QUERY_DATE, Query.Direction.DESCENDING)
}