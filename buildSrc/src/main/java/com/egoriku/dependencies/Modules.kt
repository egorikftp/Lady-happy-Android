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
}