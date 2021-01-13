package com.egoriku.ladyhappy.settings.presentation.screen

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class LoginScreen(
        private val featureProvider: IFeatureProvider
) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.loginFragment.apply {
            enterTransition = Slide(Gravity.BOTTOM)
            exitTransition = Slide(Gravity.TOP)
        }
}