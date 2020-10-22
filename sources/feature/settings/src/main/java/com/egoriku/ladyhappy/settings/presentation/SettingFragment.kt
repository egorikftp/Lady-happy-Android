package com.egoriku.ladyhappy.settings.presentation

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.core.constant.REQUEST_KEY_DYNAMIC_FEATURE
import com.egoriku.ladyhappy.core.constant.RESULT_KEY_DYNAMIC_FEATURE
import com.egoriku.ladyhappy.core.feature.DynamicFeature
import com.egoriku.ladyhappy.core.feature.SettingsFeature
import com.egoriku.extensions.browseUrl
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.FragmentSettingsBinding
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.domain.model.setting.SettingItem
import com.egoriku.ladyhappy.settings.presentation.adapter.AvailableFeaturesSectionAdapter
import com.egoriku.ladyhappy.settings.presentation.adapter.LoginSectionAdapter
import com.egoriku.ladyhappy.settings.presentation.adapter.SettingItemAdapter
import com.egoriku.ladyhappy.settings.presentation.dialog.theme.ThemeSettingDialogFragment
import com.egoriku.ladyhappy.settings.presentation.screen.LoginScreen
import com.egoriku.ladyhappy.settings.presentation.screen.UsedLibrariesScreen
import com.egoriku.ladyhappy.settings.presentation.view.State.ANON
import com.egoriku.ladyhappy.settings.presentation.view.State.LOGGED_IN
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import kotlin.properties.Delegates

class SettingFragment : Fragment(R.layout.fragment_settings), SettingsFeature {

    private val binding by viewBinding(FragmentSettingsBinding::bind)

    private val featureProvider: IFeatureProvider by inject()

    private val settingsViewModel: SettingsViewModel by lifecycleScope.viewModel(this)

    private var concatAdapter: ConcatAdapter by Delegates.notNull()

    private var loginSectionAdapter: LoginSectionAdapter by Delegates.notNull()
    private var availableFeaturesAdapter: AvailableFeaturesSectionAdapter by Delegates.notNull()
    private var settingsAdapter: SettingItemAdapter by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginSectionAdapter = LoginSectionAdapter {
            when (it) {
                ANON -> settingsViewModel.navigateTo(LoginScreen(featureProvider))
                LOGGED_IN -> settingsViewModel.logOut()
            }
        }

        availableFeaturesAdapter = AvailableFeaturesSectionAdapter {
            when (it) {
                is Feature.PublishPosts -> {
                    parentFragmentManager.setFragmentResult(
                            REQUEST_KEY_DYNAMIC_FEATURE,
                            bundleOf(RESULT_KEY_DYNAMIC_FEATURE to DynamicFeature.PostCreator)
                    )
                }
            }
        }

        settingsAdapter = SettingItemAdapter {
            when (it) {
                is SettingItem.Theme -> ThemeSettingDialogFragment().show(childFragmentManager, null)
                is SettingItem.UsedLibraries -> {
                    settingsViewModel.navigateTo(UsedLibrariesScreen(featureProvider))
                }
                is SettingItem.Review -> {
                    openPlayStore()
                }
            }
        }

        concatAdapter = ConcatAdapter(loginSectionAdapter, availableFeaturesAdapter, settingsAdapter)

        binding.initViews()

        settingsViewModel.screenData.observe(viewLifecycleOwner) {
            it.forEach { (_, section) ->
                when (section) {
                    is Section.Login -> loginSectionAdapter.submitList(listOf(section))
                    is Section.AvailableFeatures -> availableFeaturesAdapter.submitList(listOf(section))
                    is Section.Settings -> settingsAdapter.submitList(section.setting)
                }
            }
        }
    }

    private fun openPlayStore() {
        val appPackageName: String = requireContext().packageName

        try {
            browseUrl("market://details?id=$appPackageName")
        } catch (exception: ActivityNotFoundException) {
            browseUrl("https://play.google.com/store/apps/details?id=$appPackageName")
        }
    }

    private fun FragmentSettingsBinding.initViews() {
        settingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = concatAdapter
        }
    }
}