package com.egoriku.ladyhappy.mainscreen.presentation.deeplink

import android.content.Intent
import com.egoriku.ladyhappy.mainscreen.presentation.screen.params.DeepLinkScreen

class AssistanceDeepLinkParser {

    fun process(intent: Intent, navigate: (DeepLinkScreen) -> Unit) {
        val data = intent.data ?: return

        if (Intent.ACTION_VIEW == intent.action && data.scheme == "ladyhappy") {
            val fullUrl = data.toString()

            when {
                fullUrl.startsWith("ladyhappy://search") -> {
                    val searchQuery = data.getQueryParameter("query")?.trim() ?: return

                    navigate(DeepLinkScreen.Search(searchQuery))
                }
                fullUrl.startsWith("ladyhappy://feature") -> {
                    val featureName = data.getQueryParameter("featureName")?.trim() ?: return

                    when (featureName) {
                        AssistanceFeatures.CATALOG -> navigate(DeepLinkScreen.Catalog)
                        AssistanceFeatures.ABOUT -> navigate(DeepLinkScreen.About)
                        AssistanceFeatures.NEWS -> navigate(DeepLinkScreen.News)
                        AssistanceFeatures.SETTINGS -> navigate(DeepLinkScreen.Settings)
                        else -> navigate(DeepLinkScreen.Unknown)
                    }
                }
            }
        }
    }
}