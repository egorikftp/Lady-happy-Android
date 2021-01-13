package com.egoriku.ladyhappy.mainscreen.presentation.screen

import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class SearchScreen(
        private val featureProvider: IFeatureProvider,
        private val searchQuery: String
) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.getSearchFragment(searchQuery)
}
