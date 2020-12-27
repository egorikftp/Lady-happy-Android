package com.egoriku.ladyhappy.mainscreen.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.mainscreen.common.Constants
import com.egoriku.ladyhappy.mainscreen.common.TITLE_KEY
import com.egoriku.ladyhappy.mainscreen.common.TRACKING_KEY
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class SearchScreen(
        private val featureProvider: IFeatureProvider,
        private val searchQuery: String
) : FragmentScreen() {

    override val arguments: Bundle = bundleOf(
            TITLE_KEY to R.string.navigation_view_search_header,
            TRACKING_KEY to Constants.Tracking.TRACKING_FRAGMENT_SEARCH
    )

    override val fragment: Fragment
        get() = featureProvider.getSearchFragment(searchQuery)
}
