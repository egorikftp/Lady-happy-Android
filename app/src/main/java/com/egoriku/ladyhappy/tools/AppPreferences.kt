package com.egoriku.ladyhappy.tools

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.egoriku.ladyhappy.core.IAppPreferences
import com.egoriku.ladyhappy.core.sharedmodel.Theme
import com.egoriku.ladyhappy.datastoredelegates.PreferenceDataStore
import kotlinx.coroutines.flow.map

private const val PREFERENCES_NAME = "app_settings"

private val Context.dataStore by preferencesDataStore(
    name = PREFERENCES_NAME,
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, PREFERENCES_NAME))
    }
)

internal class AppPreferences(context: Context) : IAppPreferences {

    private val dataStore: DataStore<Preferences> = context.dataStore

    override var launchCount by PreferenceDataStore(
        dataStore = dataStore,
        key = intPreferencesKey(name = "launch_count"),
        defaultValue = 0
    )

    override var lastAskForReview by PreferenceDataStore(
        dataStore = dataStore,
        key = longPreferencesKey(name = "last_ask_for_review"),
        defaultValue = 0L
    )

    override var selectedTheme by PreferenceDataStore(
        dataStore = dataStore,
        key = stringPreferencesKey(name = "pref_dark_mode"),
        defaultValue = Theme.SYSTEM.storageKey
    )

    override val selectedThemeFlow = dataStore.data
        .map { selectedTheme }
}