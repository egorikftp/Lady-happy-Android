package com.egoriku.dependencies

object Modules {

    const val arch = ":arch"
    const val core = ":core"
    const val easyAdapter = ":easyAdapter"
    const val extensions = ":extensions"
    const val network = ":network"
    const val navigation = ":navigation"
    const val rendering = ":rendering"
    const val ui = ":ui"

    const val catalog = ":catalog"
    const val landing = ":landing"
    const val launchScreen = ":launchScreen"
    const val mainScreen = ":mainScreen"
    const val photoReport = ":photoReport"
    const val settings = ":settings"

    val libraries
        get() = arrayOf(
                ProjectBean(arch, "sources/base/arch"),
                ProjectBean(core, "sources/base/core"),
                ProjectBean(easyAdapter, "sources/libraries/easyAdapter"),
                ProjectBean(extensions, "sources/libraries/extensions"),
                ProjectBean(network, "sources/base/network"),
                ProjectBean(navigation, "sources/libraries/navigation"),
                ProjectBean(rendering, "sources/libraries/rendering"),
                ProjectBean(ui, "sources/base/ui")
        )

    val features
        get() = arrayOf(
                ProjectBean(catalog, "sources/feature/catalog"),
                ProjectBean(landing, "sources/feature/landing"),
                ProjectBean(launchScreen, "sources/feature/launchScreen"),
                ProjectBean(mainScreen, "sources/feature/mainScreen"),
                ProjectBean(photoReport, "sources/feature/photoReport"),
                ProjectBean(settings, "sources/feature/settings")
        )
}

class ProjectBean(
        val name: String,
        private val _path: String,
        private val isStub: Boolean = false
) {
    val path
        get() = if (isStub) "$_path/stub" else _path
}