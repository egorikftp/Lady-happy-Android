package com.egoriku.ladyhappy.data.repositories.datasource

import com.egoriku.ladyhappy.data.entities.NewsDocumentEntity
import com.egoriku.ladyhappy.data.entities.NewsEntity
import com.egoriku.ladyhappy.data.repositories.base.BaseFirebaseDataSource
import com.egoriku.ladyhappy.firebase.RxFirestore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import javax.inject.Inject

class NewsDataSourceRemote
@Inject constructor(private val firebaseFirestore: FirebaseFirestore) : BaseFirebaseDataSource() {

    private lateinit var query: Query

    private fun getCategoriesReference(): Query {
        query = firebaseFirestore
                .collection(COLLECTION_KEY_NEWS)
                .orderBy("date", Query.Direction.ASCENDING)
        return query
    }

    fun getNews(): Observable<NewsDocumentEntity> {
        return getCategoriesReference().let {
            RxFirestore.getObservableNews(it, NewsDocumentEntity(), NewsEntity::class.java)
        }
    }
}