package com.egoriku.ladyhappy.postcreator.data.remote

import com.egoriku.ladyhappy.core.IFirebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val STORAGE_CHILD = "ListAllHats/2020/"

class UploadPostImageRepository(firebase: IFirebase) {

    private val reference = firebase.firebaseStorage.reference

    suspend fun upload(fileName: String, bytes: ByteArray): String = withContext(Dispatchers.IO) {
        reference.child(STORAGE_CHILD + fileName)
                .putBytes(bytes).await()
                .storage.downloadUrl.await().toString()
    }
}