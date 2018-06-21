package com.egoriku.ladyhappy.data.repositories.datasource

import com.egoriku.core.di.IFirebaseFirestore
import com.egoriku.ladyhappy.data.entities.SingleNewsEntity
import com.egoriku.ladyhappy.data.repositories.base.BaseFirebaseDataSource
import com.egoriku.ladyhappy.firebase.getObservable

import com.google.firebase.firestore.Query
import io.reactivex.Observable
import javax.inject.Inject

class NewsDataSourceRemote
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore) : BaseFirebaseDataSource() {

    fun getNews(): Observable<List<SingleNewsEntity>> = getNewsQuery().getObservable()

    private fun getNewsQuery(): Query = firebaseFirestore
            .getFirebase()
            .collection(COLLECTION_KEY_NEWS)
            .orderBy(QUERY_DATE, Query.Direction.DESCENDING)
}