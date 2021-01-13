package com.egoriku.ladyhappy.mainscreen.presentation.screen

import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class LandingScreen(private val featureProvider: IFeatureProvider) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.landingFragment
}