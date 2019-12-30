package com.egoriku.dependencies

object Modules {

    object Applications {
        const val ladyHappy = ":app"
    }

    object Features {
        const val catalog = ":catalog"
        const val landing = ":landing"
        const val launchScreen = ":launchScreen"
        const val login = ":login"
        const val mainScreen = ":mainScreen"
        const val photoReport = ":photoReport"
        const val settings = ":settings"
    }

    object DynamicFeatures {
        const val postCreator = ":postCreator"
    }

    object Libraries {
        const val arch = ":arch"
        const val auth = ":auth"
        const val core = ":core"
        const val easyAdapter = ":easyAdapter"
        const val extensions = ":extensions"
        const val localization = ":localization"
        const val mozaik = ":mozaik"
        const val network = ":network"
        const val navigation = ":navigation"
        const val rendering = ":rendering"
        const val ui = ":ui"
    }

    val applications
        get() = arrayOf(
                ProjectBean(Applications.ladyHappy)
        )

    val dynamicFeatures
        get() = arrayOf(
                ProjectBean(DynamicFeatures.postCreator, "sources/dynamicFeature/postCreator")
        )

    val features
        get() = arrayOf(
                ProjectBean(Features.catalog, "sources/feature/catalog"),
                ProjectBean(Features.landing, "sources/feature/landing"),
                ProjectBean(Features.launchScreen, "sources/feature/launchScreen"),
                ProjectBean(Features.login, "sources/feature/login"),
                ProjectBean(Features.mainScreen, "sources/feature/mainScreen"),
                ProjectBean(Features.photoReport, "sources/feature/photoReport"),
                ProjectBean(Features.settings, "sources/feature/settings")
        )

    val libraries
        get() = arrayOf(
                ProjectBean(Libraries.arch, "sources/base/arch"),
                ProjectBean(Libraries.auth, "sources/base/auth"),
                ProjectBean(Libraries.core, "sources/base/core"),
                ProjectBean(Libraries.easyAdapter, "sources/libraries/easyAdapter"),
                ProjectBean(Libraries.extensions, "sources/libraries/extensions"),
                ProjectBean(Libraries.localization, "sources/base/localization"),
                ProjectBean(Libraries.mozaik, "sources/libraries/mozaik"),
                ProjectBean(Libraries.network, "sources/base/network"),
                ProjectBean(Libraries.navigation, "sources/libraries/navigation"),
                ProjectBean(Libraries.rendering, "sources/libraries/rendering"),
                ProjectBean(Libraries.ui, "sources/base/ui")
        )
}

class ProjectBean(
        val name: String,
        val path: String = ""
)