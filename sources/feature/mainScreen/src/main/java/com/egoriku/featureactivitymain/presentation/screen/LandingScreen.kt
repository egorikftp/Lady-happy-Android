package com.egoriku.featureactivitymain.presentation.screen

import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.featureprovider.provider.FeatureScreen
import ru.terrakok.cicerone.android.support.SupportAppScreen

class LandingScreen : SupportAppScreen() {

    override fun getFragment(): Fragment = FeatureScreen.getLandingFragment()
}