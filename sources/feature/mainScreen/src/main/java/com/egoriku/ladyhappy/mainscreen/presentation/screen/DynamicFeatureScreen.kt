package com.egoriku.ladyhappy.mainscreen.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.extensions.getClassByName
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.google.android.material.transition.MaterialSharedAxis

class DynamicFeatureScreen(
        private val fragmentName: String
) : FragmentScreen() {

    override val arguments: Bundle = bundleOf()

    override val fragment: Fragment
        get() = getClassByName<Fragment>(fragmentName).apply {
            enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
            returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        }
}