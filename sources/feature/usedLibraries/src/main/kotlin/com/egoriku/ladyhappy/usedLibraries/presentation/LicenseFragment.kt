package com.egoriku.ladyhappy.usedLibraries.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.extensions.extraNotNull
import com.egoriku.ladyhappy.usedLibraries.R
import com.egoriku.ladyhappy.usedLibraries.databinding.FragmentLicenseBinding

internal class LicenseFragment : Fragment(R.layout.fragment_license) {

    private val binding by viewBinding(FragmentLicenseBinding::bind)

    private val license: String by extraNotNull("license")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.text.text = license

        if (savedInstanceState != null) {
            val int = savedInstanceState.getInt("scroll_pos")

            binding.scrollView.scrollTo(0, int)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("scroll_pos", binding.scrollView.scrollY)
    }
}