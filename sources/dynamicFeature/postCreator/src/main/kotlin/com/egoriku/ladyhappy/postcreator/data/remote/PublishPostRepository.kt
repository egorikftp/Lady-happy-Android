package com.egoriku.ladyhappy.postcreator.data.remote

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.core.sharedmodel.key.CollectionPath.ALL_HATS
import com.egoriku.ladyhappy.extensions.logDm
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.postcreator.data.entity.UploadEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PublishPostRepository(firebase: IFirebase) {

    private val reference = firebase.firebaseFirestore

    suspend fun publish(uploadEntity: UploadEntity): ResultOf<Boolean> =
        withContext(Dispatchers.IO) {
            runCatching {
                reference.collection(ALL_HATS)
                    .document()
                    .set(uploadEntity)
                    .addOnFailureListener { e -> logDm("Error adding document $e") }
                    .await()

                ResultOf.Success(true)
            }.getOrElse {
                ResultOf.Failure(it)
            }
        }
}