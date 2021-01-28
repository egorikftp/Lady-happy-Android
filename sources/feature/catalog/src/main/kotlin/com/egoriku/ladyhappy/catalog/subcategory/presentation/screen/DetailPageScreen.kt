package com.egoriku.ladyhappy.catalog.subcategory.presentation.screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough

class DetailPageScreen(
        private val featureProvider: IFeatureProvider,
        private val subCategoryId: Int,
        private val url: String,
        private val name: String,
) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.detailPage.apply {
            enterTransition = MaterialFadeThrough()
            returnTransition = MaterialFade()

            arguments = bundleOf(
                    "subCategoryId" to subCategoryId,
                    "name" to name,
                    "url" to url
            )
        }
}