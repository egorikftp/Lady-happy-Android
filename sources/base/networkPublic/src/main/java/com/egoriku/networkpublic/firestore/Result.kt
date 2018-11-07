package com.egoriku.networkpublic.firestore

sealed class Result<out T> {
    class Success<out T>(val value: T) : Result<T>()
    class Error(val exception: Throwable) : Result<Nothing>()
}

inline fun <T> wrapIntoResult(coroutineBlock: () -> T): Result<T> = try {
    Result.Success(coroutineBlock())
} catch (exception: Exception) {
    Result.Error(exception)
}