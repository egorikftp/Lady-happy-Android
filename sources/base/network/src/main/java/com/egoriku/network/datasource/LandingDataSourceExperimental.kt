package com.egoriku.network.datasource

import com.egoriku.core.di.utils.IFirebaseFirestore
import com.egoriku.network.data.entities.landing.LandingEntity
import com.egoriku.network.firestore.awaitGet
import com.egoriku.network.firestore.getObservable
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class LandingDataSourceExperimental
@Inject constructor(private val firebaseFirestore: IFirebaseFirestore) {

    companion object {
        const val LANDING_PATH = "landingInfo"
        const val DOCUMENT_PATH = "MCwd73rZNcwDE4yjP3bj"
    }

    suspend fun downloadLanding(): LandingEntity = getLandingQuery().awaitGet()

    private fun getLandingQuery(): DocumentReference = firebaseFirestore
            .getFirebase()
            .collection(LANDING_PATH)
            .document(DOCUMENT_PATH)
}