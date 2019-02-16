package com.egoriku.landingfragment.action

import androidx.fragment.app.Fragment
import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.landingfragment.presentation.LandingPageFragment

internal class LandingFragmentAction : ILandingFragmentAction {

    override fun provideFragment(): Fragment = LandingPageFragment.newInstance()
}