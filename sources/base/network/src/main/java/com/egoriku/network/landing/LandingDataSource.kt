package com.egoriku.network.landing

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.network.getObservable
import com.egoriku.network.landing.entity.LandingEntity
import com.google.firebase.firestore.DocumentReference
import io.reactivex.Observable
import javax.inject.Inject

class LandingDataSource
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore) {

    companion object {
        const val LANDING_PATH = "landingInfo"
        const val DOCUMENT_PATH = "MCwd73rZNcwDE4yjP3bj"
    }

    fun downloadLanding(): Observable<LandingEntity> = getLandingQuery().getObservable()

    private fun getLandingQuery(): DocumentReference = firebaseFirestore
            .getFirebase()
            .collection(LANDING_PATH)
            .document(DOCUMENT_PATH)
}