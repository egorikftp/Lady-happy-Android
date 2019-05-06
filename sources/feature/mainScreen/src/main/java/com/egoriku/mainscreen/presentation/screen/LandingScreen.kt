package com.egoriku.mainscreen.presentation.screen

import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.featureprovider.provider.FeatureScreen
import com.egoriku.mainscreen.R
import com.egoriku.mainscreen.common.Constants.Tracking
import com.egoriku.mainscreen.presentation.screen.base.AppScreen

class LandingScreen : AppScreen() {

    override val pageTitle: Int
        get() = R.string.navigation_view_landing_header

    override val trackingKey: String
        get() = Tracking.TRACKING_FRAGMENT_LANDING

    override fun getFragment(): Fragment = FeatureScreen.getLandingFragment()
}