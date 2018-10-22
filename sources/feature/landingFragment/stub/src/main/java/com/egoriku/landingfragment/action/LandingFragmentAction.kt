package com.egoriku.landingfragment.action

import androidx.fragment.app.Fragment
import com.egoriku.core.actions.ILandingFragmentAction

internal class LandingFragmentAction : ILandingFragmentAction {

    override fun provideFragment(): Fragment = Fragment()
}