package com.egoriku.ladyhappy.postcreator.data.remote

import com.egoriku.ladyhappy.core.IFirebase
import com.egoriku.ladyhappy.network.firestore.awaitImageUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val STORAGE_CHILD = "ListAllHats/2020/"

class UploadPostImageRepository(firebase: IFirebase) {

    private val reference = firebase.firebaseStorage.reference

    suspend fun upload(fileName: String, bytes: ByteArray): String = withContext(Dispatchers.IO) {
        with(reference.child(STORAGE_CHILD + fileName)) {
            putBytes(bytes).awaitImageUrl(this)
        }
    }
}