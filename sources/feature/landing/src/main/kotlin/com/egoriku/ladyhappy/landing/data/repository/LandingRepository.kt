package com.egoriku.ladyhappy.landing.data.repository

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.constant.CollectionPath.LANDING
import com.egoriku.ladyhappy.landing.data.entity.LandingEntity
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.firestore.awaitGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val DOCUMENT_PATH = "MCwd73rZNcwDE4yjP3bj"

internal class LandingRepository(
        private val firebase: IFirebase
) : ILandingRepository {

    override suspend fun getLanding() = withContext(Dispatchers.IO) {
        runCatching {
            val value = firebase
                    .firebaseFirestore
                    .collection(LANDING)
                    .document(DOCUMENT_PATH)
                    .awaitGet<LandingEntity>()

            ResultOf.Success(value)
        }.getOrElse {
            ResultOf.Failure(it)
        }
    }
}

interface ILandingRepository {

    suspend fun getLanding(): ResultOf<LandingEntity>
}