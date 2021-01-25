package com.egoriku.ladyhappy.network.firestore

import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.wrapIntoResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.tasks.await

suspend inline fun <T> Task<T>.awaitResult(): ResultOf<T> = wrapIntoResult { await() }