package com.egoriku.giugi.data.repositories.datasource

import com.egoriku.giugi.data.entities.CategoriesEntity
import com.egoriku.giugi.data.entities.CategoryEntity
import com.egoriku.giugi.firebase.RxFirestore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import javax.inject.Inject

class CategoriesDataSourceRemote
@Inject constructor(private val firebaseFirestore: FirebaseFirestore) : BaseFirebaseDataSource() {

    private lateinit var query: Query

    private fun getCategoriesReference(): Query {
        query = firebaseFirestore
                .collection(COLLECTION_KEY_CATEGORIES)
                .orderBy("id", Query.Direction.ASCENDING)
        return query
    }

    fun getCategories(): Observable<CategoriesEntity> {
        return getCategoriesReference().let {
            RxFirestore.getObservableCategories(it, CategoriesEntity(), CategoryEntity::class.java)
        }
    }
}