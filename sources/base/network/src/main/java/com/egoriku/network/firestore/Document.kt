package com.egoriku.network.firestore

import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.exception.NoSuchDocumentException
import com.egoriku.network.Result
import com.egoriku.network.wrapIntoResult
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <reified T> DocumentReference.awaitGetResult(): Result<T> =
        wrapIntoResult {
            awaitGet(T::class.java)
        }

suspend fun <T> DocumentReference.awaitGet(type: Class<T>): T = suspendCancellableCoroutine { continuation ->
    this.get().addOnSuccessListener { snapshot ->
        if (snapshot.exists()) {
            try {
                val data: T? = snapshot.toObject(type)
                data?.let {
                    continuation.resume(it)
                }
                        ?: continuation.resumeWithException(FirestoreParseException("Failed to get document from $this of type: $type"))
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }
        } else {
            continuation.resumeWithException(NoSuchDocumentException())
        }
    }.addOnFailureListener {
        continuation.resumeWithException(FirestoreNetworkException(it.message))
    }
}