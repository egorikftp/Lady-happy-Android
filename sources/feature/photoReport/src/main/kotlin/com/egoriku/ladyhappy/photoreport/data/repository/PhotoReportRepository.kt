package com.egoriku.ladyhappy.photoreport.data.repository

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitGet
import com.egoriku.ladyhappy.photoreport.data.entity.PhotoReportEntity
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val COLLECTION_KEY_NEWS = "news"
private const val QUERY_DATE = "date"

class PhotoReportRepository(private val firebase: IFirebase) {

    suspend fun getPhotoReport(): ResultOf<List<PhotoReportEntity>> = withContext(Dispatchers.IO) {
        runCatching {
            val value = firebase
                    .firebaseFirestore
                    .collection(COLLECTION_KEY_NEWS)
                    .orderBy(QUERY_DATE, Query.Direction.DESCENDING)
                    .awaitGet<PhotoReportEntity>()

            if (value.isEmpty()) {
                ResultOf.Failure(Exception("Empty response"))
            } else {
                ResultOf.Success(value)
            }
        }.getOrElse {
            ResultOf.Failure(it)
        }
    }
}