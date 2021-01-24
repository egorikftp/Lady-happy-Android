package com.egoriku.ladyhappy.postcreator.data.remote

import com.egoriku.ladyhappy.core.IFirebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UploadImageRepository(firebase: IFirebase) {

    private val reference = firebase.firebaseStorage.reference

    suspend fun upload(
            storagePath: String,
            fileName: String,
            bytes: ByteArray,
    ): String = withContext(Dispatchers.IO) {
        reference.child(storagePath + fileName)
                .putBytes(bytes).await()
                .storage.downloadUrl.await().toString()
    }
}