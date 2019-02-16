package com.egoriku.network.firestore.corouines

import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.firestore.Result
import com.egoriku.core.firestore.wrapIntoResult
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <reified T> Query.awaitGet(): List<T> = get().awaitGet()

suspend inline fun <reified T> Query.awaitGetResult(): Result<List<T>> = wrapIntoResult { awaitGet<T>() }

private suspend fun <T> awaitTaskQueryList(task: Task<QuerySnapshot>, type: Class<T>): List<T> =
        suspendCancellableCoroutine { continuation ->
            task.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    try {
                        val data: List<T> = task.result?.toObjects(type).orEmpty()
                        continuation.resume(data)
                    } catch (exception: Exception) {
                        continuation.resumeWithException(exception)
                    }
                } else {
                    continuation.resumeWithException(task.exception
                            ?: FirestoreParseException("Failed to read task list: $task of type: $type"))
                }
            }.addOnFailureListener {
                continuation.resumeWithException(FirestoreNetworkException(it.message))
            }
        }

suspend fun <T> Task<QuerySnapshot>.awaitGet(type: Class<T>): List<T> = awaitTaskQueryList(this, type)

suspend inline fun <reified T> Task<QuerySnapshot>.awaitGet(): List<T> = this.awaitGet(T::class.java)

suspend inline fun <reified T> Task<QuerySnapshot>.awaitGetResult(): Result<List<T>> =
        wrapIntoResult { this.awaitGet<T>() }