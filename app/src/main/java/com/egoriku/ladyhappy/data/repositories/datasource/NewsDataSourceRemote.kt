package com.egoriku.ladyhappy.data.repositories.datasource

import com.egoriku.ladyhappy.data.entities.SingleNewsEntity
import com.egoriku.ladyhappy.data.repositories.base.BaseFirebaseDataSource
import com.egoriku.ladyhappy.firebase.getObservable
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import javax.inject.Inject

class NewsDataSourceRemote
@Inject constructor(private val firebaseFirestore: FirebaseFirestore) : BaseFirebaseDataSource() {

    fun getNews(): Observable<List<SingleNewsEntity>> = getNewsQuery().getObservable()

    private fun getNewsQuery(): Query = firebaseFirestore
            .collection(COLLECTION_KEY_NEWS)
            .orderBy(QUERY_DATE, Query.Direction.DESCENDING)
}