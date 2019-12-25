package com.egoriku.ladyhappy.settings.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.extensions.colorCompat
import com.egoriku.ladyhappy.extensions.toUri
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.FragmentSettingsBinding

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentSettingsBinding.inflate(inflater, container, false).apply {
            binding = this
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyBottomMargin()

        binding.termsOfServiceButton.setOnClickListener {
            CustomTabsIntent.Builder().apply {
                setToolbarColor(colorCompat(R.color.RoseTaupe))
                build().apply {
                    launchUrl(context, getString(R.string.terms_of_service_link).toUri())
                }
            }
        }

        binding.privacyPolicyButton.setOnClickListener {
            CustomTabsIntent.Builder().apply {
                setToolbarColor(colorCompat(R.color.RoseTaupe))
                build().apply {
                    launchUrl(context, getString(R.string.privacy_policy_link).toUri())
                }
            }
        }
    }

    private fun applyBottomMargin() {
        requireActivity().findViewById<View>(R.id.bottomNavigation).doOnPreDraw {
            with(binding.termsOfServiceButton) {
                layoutParams = (layoutParams as ConstraintLayout.LayoutParams).apply {
                    bottomMargin = it.height * 2
                }
            }
        }
    }
}