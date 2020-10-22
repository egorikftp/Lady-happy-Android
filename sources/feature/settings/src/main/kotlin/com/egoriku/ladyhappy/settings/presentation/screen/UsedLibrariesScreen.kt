package com.egoriku.ladyhappy.settings.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.google.android.material.transition.MaterialSharedAxis

class UsedLibrariesScreen(
        private val featureProvider: IFeatureProvider
) : FragmentScreen() {

    override val fragment: Fragment
        get() = featureProvider.usedLibrariesFragment.apply {
            enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
            returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        }

    override val arguments: Bundle
        get() = bundleOf()
}