package com.egoriku.network.firestore

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Source
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirestoreException(errorMessage: String) : Exception(errorMessage)

suspend inline fun <reified T> DocumentReference.awaitGet(source: Source = Source.DEFAULT): T = awaitDocumentValue(this, source)

//Single
suspend inline fun <reified T> awaitDocumentValue(document: DocumentReference, source: Source = Source.DEFAULT): T =
        suspendCancellableCoroutine { continuation ->
            document.get(source).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    try {
                        val data: T? = task.result?.toObject(T::class.java)
                        data?.let {
                            continuation.resume(it)
                        }
                                ?: continuation.resumeWithException(FirestoreException("Failed to get document from $document of type: ${T::class.java}"))
                    } catch (exception: Exception) {
                        continuation.resumeWithException(FirestoreException("Failed to get document from $document of type: ${T::class.java}"))
                    }
                }
            }
        }