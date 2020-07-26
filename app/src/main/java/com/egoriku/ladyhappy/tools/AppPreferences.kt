package com.egoriku.ladyhappy.tools

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import com.egoriku.core.IAppPreferences
import com.egoriku.core.sharedmodel.Theme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private const val PREFERENCES_NAME = "app_settings"
private const val PREF_DARK_MODE_ENABLED = "pref_dark_mode"

@FlowPreview
@ExperimentalCoroutinesApi
internal class AppPreferences(context: Context) : IAppPreferences {

    private val prefs: Lazy<SharedPreferences> = lazy { // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
                PREFERENCES_NAME, MODE_PRIVATE
        ).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val selectedThemeChannel: ConflatedBroadcastChannel<String> by lazy {
        ConflatedBroadcastChannel<String>().also { channel ->
            channel.offer(selectedTheme)
        }
    }

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            PREF_DARK_MODE_ENABLED -> selectedThemeChannel.offer(selectedTheme)
        }
    }

    override var selectedTheme by StringPreference(prefs, PREF_DARK_MODE_ENABLED, Theme.SYSTEM.storageKey)

    override var observableSelectedTheme: Flow<String>
        get() = selectedThemeChannel.asFlow()
        set(_) = throw IllegalAccessException("This property can't be changed")
}

class StringPreference(
        private val preferences: Lazy<SharedPreferences>,
        private val name: String,
        private val defaultValue: String
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preferences.value.getString(name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.value.edit { putString(name, value) }
    }
}

class BooleanPreference(
        private val preferences: Lazy<SharedPreferences>,
        private val name: String,
        private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.value.edit { putBoolean(name, value) }
    }
}