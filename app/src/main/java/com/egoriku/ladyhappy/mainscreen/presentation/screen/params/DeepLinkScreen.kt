package com.egoriku.ladyhappy.mainscreen.presentation.screen.params

sealed class DeepLinkScreen {
    data class Search(
        val searchQuery: String
    ) : DeepLinkScreen()

    object Catalog : DeepLinkScreen()
    object About : DeepLinkScreen()
    object News : DeepLinkScreen()
    object Settings : DeepLinkScreen()
    object Unknown : DeepLinkScreen()
}