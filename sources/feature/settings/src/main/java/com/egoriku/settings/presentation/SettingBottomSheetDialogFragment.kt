package com.egoriku.settings.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import com.egoriku.ladyhappy.arch.dialogfragment.BaseBottomSheetDialogFragment
import com.egoriku.ladyhappy.extensions.colorCompat
import com.egoriku.ladyhappy.extensions.toUri
import com.egoriku.settings.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_settings_bottom_sheet.*

class SettingBottomSheetDialogFragment : BaseBottomSheetDialogFragment() {

    override val layoutId: Int = R.layout.fragment_settings_bottom_sheet

    override fun getTheme(): Int = R.style.BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}