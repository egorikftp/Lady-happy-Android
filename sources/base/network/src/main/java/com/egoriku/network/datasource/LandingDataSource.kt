package com.egoriku.network.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.network.data.entities.landing.LandingEntity
import com.egoriku.network.firestore.corouines.awaitGetResult
import com.egoriku.core.firestore.Result
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class LandingDataSource
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore) {

    companion object {
        const val LANDING_PATH = "landingInfo"
        const val DOCUMENT_PATH = "MCwd73rZNcwDE4yjP3bj"
    }

    suspend fun downloadLanding(): Result<LandingEntity> = getLandingQuery().awaitGetResult()

    private fun getLandingQuery(): DocumentReference = firebaseFirestore
            .getFirebase()
            .collection(LANDING_PATH)
            .document(DOCUMENT_PATH)
}