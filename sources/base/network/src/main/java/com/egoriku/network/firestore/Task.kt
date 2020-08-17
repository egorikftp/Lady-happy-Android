package com.egoriku.network.firestore

import com.egoriku.network.ResultOf
import com.egoriku.network.wrapIntoResult
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <T> Task<T>.awaitResult(): ResultOf<T> = wrapIntoResult { this.await() }

suspend fun <T> Task<T>.await(): T = suspendCancellableCoroutine { continuation ->
    addOnCompleteListener { task ->
        if (task.isSuccessful) {
            task.result?.let {
                continuation.resume(it)
            } ?: continuation.resumeWithException(Exception("Firebase Task returned null"))
        } else {
            continuation.resumeWithException(task.exception
                    ?: Exception("Firebase Task failed to execute"))
        }
    }
}

suspend fun UploadTask.awaitImageUrl(childReference: StorageReference): String =
        suspendCancellableCoroutine { continuation ->
            continueWithTask { childReference.downloadUrl }
            addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let {
                        continuation.resume(it.toString())
                    } ?: continuation.resumeWithException(Exception("Firebase Task returned null"))
                } else {
                    continuation.resumeWithException(task.exception
                            ?: Exception("Firebase UploadTask failed to execute"))
                }
            }
        }