package com.egoriku.ladyhappy.network.firestore

import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.exception.FirestoreParseException
import com.egoriku.ladyhappy.network.exception.NoSuchDocumentException
import com.egoriku.ladyhappy.network.wrapIntoResult
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <reified T> DocumentReference.awaitResult(): ResultOf<T> =
        wrapIntoResult {
            awaitGet(T::class.java)
        }

suspend inline fun <reified T> DocumentReference.awaitGet(): T = awaitGet(T::class.java)

suspend fun <T> DocumentReference.awaitGet(type: Class<T>): T = suspendCancellableCoroutine { continuation ->
    get().addOnSuccessListener { snapshot ->
        if (snapshot.exists()) {
            try {
                val data: T? = snapshot.toObject(type)
                data?.let {
                    continuation.resume(it)
                }
                        ?: continuation.resumeWithException(
                                FirestoreParseException("Failed to get document from $this of type: $type")
                        )
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }
        } else {
            continuation.resumeWithException(NoSuchDocumentException())
        }
    }.addOnFailureListener {
        continuation.resumeWithException(it)
    }
}