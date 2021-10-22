package com.egoriku.ladyhappy.datastoredelegates

import androidx.annotation.WorkerThread
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceDataStore<T>(
    private val dataStore: DataStore<Preferences>,
    private val key: Preferences.Key<T>,
    private val defaultValue: T
) : ReadWriteProperty<Any, T> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        dataStore.get(
            key = key,
            defaultValue = defaultValue
        )

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        dataStore.set(
            key = key,
            value = value
        )
    }
}