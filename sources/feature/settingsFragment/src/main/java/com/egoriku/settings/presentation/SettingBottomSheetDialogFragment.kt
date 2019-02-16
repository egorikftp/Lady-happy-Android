package com.egoriku.settings.presentation

import android.app.Dialog
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import com.egoriku.core.di.findDependencies
import com.egoriku.ladyhappy.extensions.colorCompat
import com.egoriku.settings.R
import com.egoriku.settings.di.SettingsFragmentComponent
import com.egoriku.settings.presentation.base.BaseBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_settings_bottom_sheet.*
import javax.inject.Inject

class SettingBottomSheetDialogFragment : BaseBottomSheetDialogFragment<SettingsPageContract.View, SettingsPageContract.Presenter>(), SettingsPageContract.View {

    @Inject
    lateinit var settingsPresenter: SettingsPageContract.Presenter

    override fun injectDependencies() = SettingsFragmentComponent.init(findDependencies()).inject(this)

    override fun providePresenter(): SettingsPageContract.Presenter = settingsPresenter

    override fun getLayoutId(): Int = R.layout.fragment_settings_bottom_sheet

    override fun getTheme(): Int = R.style.BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        termsOfServiceButton.setOnClickListener {
            CustomTabsIntent.Builder().apply {
                setToolbarColor(context?.colorCompat(R.color.RoseTaupe) ?: Color.WHITE)
                build().apply {
                    launchUrl(context, Uri.parse(getString(R.string.terms_of_service_link)))
                }
            }
        }

        privacyPolicyButton.setOnClickListener {
            CustomTabsIntent.Builder().apply {
                setToolbarColor(context?.colorCompat(R.color.RoseTaupe) ?: Color.WHITE)
                build().apply {
                    launchUrl(context, Uri.parse(getString(R.string.privacy_policy_link)))
                }
            }
        }
    }
}