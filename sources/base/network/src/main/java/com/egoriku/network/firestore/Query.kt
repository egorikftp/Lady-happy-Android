package com.egoriku.network.firestore

import android.util.Log
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.network.ResultOf
import com.egoriku.network.wrapIntoResult
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <reified T> Query.awaitResult(): ResultOf<List<T>> = wrapIntoResult { awaitGet<T>() }

suspend inline fun <reified T> Query.awaitGet(): List<T> = get().awaitGet()

suspend inline fun <reified T> Task<QuerySnapshot>.awaitGet(): List<T> = awaitGet(T::class.java)

suspend fun <T> Task<QuerySnapshot>.awaitGet(type: Class<T>): List<T> = awaitTaskQueryList(this, type)

private suspend fun <T> awaitTaskQueryList(task: Task<QuerySnapshot>, type: Class<T>): List<T> =
        suspendCancellableCoroutine { continuation ->
            task.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    try {
                        val data: List<T> = task.result?.toObjects(type).orEmpty()
                        continuation.resume(data)
                    } catch (exception: Exception) {
                        Log.d("egorikftp", exception.toString())
                        continuation.resumeWithException(exception)
                    }
                } else {
                    continuation.resumeWithException(
                            task.exception
                                    ?: FirestoreParseException("Failed to read task list: $task of type: $type"))
                }
            }
        }

suspend inline fun <reified T> Task<QuerySnapshot>.awaitResult(): ResultOf<List<T>> =
        wrapIntoResult { awaitGet<T>() }