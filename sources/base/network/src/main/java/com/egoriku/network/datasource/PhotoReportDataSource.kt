package com.egoriku.network.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.network.data.entities.photoreport.PhotoReportEntity
import com.egoriku.network.getObservable
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import javax.inject.Inject

class PhotoReportDataSource
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore) {
    companion object {
        const val COLLECTION_KEY_NEWS = "news"
        const val QUERY_DATE = "date"
    }

    fun downloadPhotoReport(): Observable<List<PhotoReportEntity>> = getPhotoReportQuery().getObservable()

    private fun getPhotoReportQuery(): Query = firebaseFirestore
            .getFirebase()
            .collection(COLLECTION_KEY_NEWS)
            .orderBy(QUERY_DATE, Query.Direction.DESCENDING)
}