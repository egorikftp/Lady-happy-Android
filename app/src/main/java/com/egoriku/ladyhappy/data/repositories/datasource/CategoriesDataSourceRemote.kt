package com.egoriku.ladyhappy.data.repositories.datasource

import com.egoriku.ladyhappy.data.entities.CategoryEntity
import com.egoriku.ladyhappy.data.repositories.base.BaseFirebaseDataSource
import com.egoriku.ladyhappy.firebase.getObservable
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import javax.inject.Inject

class CategoriesDataSourceRemote
@Inject constructor(private val firebaseFirestore: FirebaseFirestore) : BaseFirebaseDataSource() {

    fun getCategories(): Observable<List<CategoryEntity>> = getCategoriesReference().getObservable()

    private fun getCategoriesReference(): Query = firebaseFirestore
            .collection(COLLECTION_KEY_CATEGORIES)
            .orderBy(QUERY_ID, Query.Direction.ASCENDING)
}