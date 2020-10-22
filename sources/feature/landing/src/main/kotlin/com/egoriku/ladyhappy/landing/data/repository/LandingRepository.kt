package com.egoriku.ladyhappy.landing.data.repository

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.landing.data.entity.LandingEntity
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitGet
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