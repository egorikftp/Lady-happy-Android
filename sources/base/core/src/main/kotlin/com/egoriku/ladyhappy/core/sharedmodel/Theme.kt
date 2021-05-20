package com.egoriku.ladyhappy.core.sharedmodel

import androidx.appcompat.app.AppCompatDelegate

/**
 * Represents the available UI themes for the application
 */
enum class Theme(val storageKey: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system"),
    BATTERY_SAVER("battery_saver")
}

/**
 * Returns the matching [Theme] for the given [storageKey] value.
 */
fun themeFromStorageKey(storageKey: String): Theme? =
    Theme.values().firstOrNull { it.storageKey == storageKey }

fun Theme.toNightMode() = when (this) {
    Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
    Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
    Theme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    Theme.BATTERY_SAVER -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
}