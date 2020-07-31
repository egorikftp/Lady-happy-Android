package com.egoriku.usedLibraries.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.extensions.extraNotNull
import com.egoriku.usedLibraries.R
import com.egoriku.usedLibraries.databinding.FragmentLicenseBinding

internal class LicenseFragment : Fragment(R.layout.fragment_license) {

    private val binding: FragmentLicenseBinding by viewBinding()

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