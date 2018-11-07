package com.egoriku.core.firestore

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <T> Task<T>.awaitResult(): Result<T> = wrapIntoResult { this.await() }

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

