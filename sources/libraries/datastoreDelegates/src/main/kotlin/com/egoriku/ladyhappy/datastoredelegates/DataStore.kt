@file:Suppress("NOTHING_TO_INLINE")

package com.egoriku.ladyhappy.datastoredelegates

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

internal inline fun <T> DataStore<Preferences>.get(
    key: Preferences.Key<T>,
    defaultValue: T
): T = runBlocking {
    data.first()[key] ?: defaultValue
}

internal inline fun <T> DataStore<Preferences>.set(
    key: Preferences.Key<T>,
    value: T?
) = runBlocking<Unit> {
    edit {
        if (value == null) {
            it.remove(key)
        } else {
            it[key] = value
        }
    }
}
