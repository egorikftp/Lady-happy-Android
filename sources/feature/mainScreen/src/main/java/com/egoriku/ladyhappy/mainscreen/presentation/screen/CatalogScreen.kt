package com.egoriku.ladyhappy.mainscreen.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.mainscreen.common.Constants.Tracking
import com.egoriku.ladyhappy.mainscreen.common.TITLE_KEY
import com.egoriku.ladyhappy.mainscreen.common.TRACKING_KEY
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.egoriku.mainscreen.R

class CatalogScreen(private val featureProvider: IFeatureProvider) : FragmentScreen() {

    override val arguments: Bundle = bundleOf(
            TITLE_KEY to R.string.navigation_view_catalog_header,
            TRACKING_KEY to Tracking.TRACKING_FRAGMENT_CATALOG
    )

    override val fragment: Fragment
        get() = featureProvider.catalogFragment
}
