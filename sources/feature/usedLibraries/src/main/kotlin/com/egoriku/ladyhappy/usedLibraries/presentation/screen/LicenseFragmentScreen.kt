package com.egoriku.ladyhappy.usedLibraries.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.egoriku.ladyhappy.usedLibraries.presentation.LicenseFragment

class LicenseFragmentScreen(private val licence: String) : FragmentScreen() {

    override val arguments: Bundle = bundleOf()

    override val fragment: Fragment
        get() = LicenseFragment().apply {
            arguments = bundleOf("license" to licence)
        }
}