package com.egoriku.giugi.data.repositories.datasource

import com.egoriku.giugi.data.entities.AllGoodsEntity
import com.egoriku.giugi.firebase.RxFirebase
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import com.google.firebase.database.DatabaseReference
import io.reactivex.Observable


class AllGoodsDataSourceRemote
@Inject constructor(private val firebaseDatabase: FirebaseDatabase)
    : BaseFirebaseDataSource() {

    private var childReference: DatabaseReference? = null

    fun getChildReference(): DatabaseReference? {
        if (childReference == null) {
            childReference = firebaseDatabase
                    .reference
                    .child(FIREBASE_CHILD_KEY_USERS)
        }

        return childReference
    }

    fun getBucket(): Observable<AllGoodsEntity>? {
        return getChildReference()?.let { RxFirebase.getObservable(it, AllGoodsEntity::class.java) }
    }
}