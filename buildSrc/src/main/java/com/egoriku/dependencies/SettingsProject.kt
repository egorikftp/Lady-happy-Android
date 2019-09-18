package com.egoriku.dependencies

object SettingsProject {
    const val archProject = ":arch"
    const val easyAdapter = ":easyAdapter"
    const val extensions = ":extensions"

    val libraries
        get() = arrayOf(archProject, easyAdapter, extensions)
}