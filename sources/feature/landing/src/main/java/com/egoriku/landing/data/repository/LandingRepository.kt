package com.egoriku.landing.data.repository

import com.egoriku.core.IFirebase
import com.egoriku.landing.data.entity.LandingEntity
import com.egoriku.network.ResultOf
import com.egoriku.network.firestore.awaitGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val LANDING_PATH = "landingInfo"
private const val DOCUMENT_PATH = "MCwd73rZNcwDE4yjP3bj"

class LandingRepository(private val firebase: IFirebase) {

    suspend fun getLanding(): ResultOf<LandingEntity> = withContext(Dispatchers.IO) {
        runCatching {
            val value: LandingEntity = firebase
                    .firebaseFirestore
                    .collection(LANDING_PATH)
                    .document(DOCUMENT_PATH)
                    .awaitGet()

            ResultOf.Success(value)
        }.getOrElse {
            ResultOf.Failure(it)
        }
    }
}