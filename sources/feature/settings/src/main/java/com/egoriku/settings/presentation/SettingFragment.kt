package com.egoriku.settings.presentation

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.extensions.colorCompat
import com.egoriku.ladyhappy.extensions.toUri
import com.egoriku.settings.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyBottomMargin()

        termsOfServiceButton.setOnClickListener {
            CustomTabsIntent.Builder().apply {
                setToolbarColor(colorCompat(R.color.RoseTaupe))
                build().apply {
                    launchUrl(context, getString(R.string.terms_of_service_link).toUri())
                }
            }
        }

        privacyPolicyButton.setOnClickListener {
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
            with(termsOfServiceButton) {
                layoutParams = (layoutParams as ConstraintLayout.LayoutParams).apply {
                    bottomMargin = it.height * 2
                }
            }
        }
    }
}