package com.egoriku.network

inline fun <T> wrapIntoResult(coroutineBlock: () -> T): ResultOf<T> = try {
    ResultOf.Success(coroutineBlock())
} catch (exception: Exception) {
    ResultOf.Failure(exception)
}