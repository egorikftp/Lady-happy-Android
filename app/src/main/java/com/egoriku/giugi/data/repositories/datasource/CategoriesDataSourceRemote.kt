package com.egoriku.giugi.data.repositories.datasource

import com.egoriku.giugi.data.entities.AllGoodsEntity
import com.egoriku.giugi.firebase.RxFirebase
import javax.inject.Inject
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable

class CategoriesDataSourceRemote
@Inject constructor(private val firebaseFirestore: FirebaseFirestore)
    : BaseFirebaseDataSource() {

    private var query: Query? = null

    private fun getCategoriesReference(): Query? {
        if (query == null) {
            query = firebaseFirestore
                    .collection(COLLECTION_KEY_CATEGORIES).orderBy("id")
        }

        return query
    }

    fun getCategories(): Observable<AllGoodsEntity>? =
            getCategoriesReference()?.let { RxFirebase.getObservable(it, AllGoodsEntity::class.java) }
}