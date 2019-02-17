package com.egoriku.network

inline fun <T> wrapIntoResult(coroutineBlock: () -> T): Result<T> = try {
    Result.Success(coroutineBlock())
} catch (exception: Exception) {
    Result.Error(exception)
}