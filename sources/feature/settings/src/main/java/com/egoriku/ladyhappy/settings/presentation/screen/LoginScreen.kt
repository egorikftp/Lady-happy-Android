package com.egoriku.ladyhappy.settings.presentation.screen

import android.os.Bundle
import android.view.Gravity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.egoriku.core.IFeatureProvider
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class LoginScreen(
        private val featureProvider: IFeatureProvider
) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.loginFragment.apply {
            enterTransition = Slide(Gravity.BOTTOM)
            exitTransition = Slide(Gravity.TOP)
        }

    override val arguments: Bundle
        get() = bundleOf()
}