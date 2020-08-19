package com.egoriku.ladyhappy.settings.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.IFeatureProvider
import com.egoriku.core.connector.IDynamicFeatureConnector
import com.egoriku.core.feature.SettingsFeature
import com.egoriku.extensions.toast
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.FragmentSettingsBinding
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.domain.model.setting.SettingItem
import com.egoriku.ladyhappy.settings.presentation.adapter.AvailableFeaturesAdapter
import com.egoriku.ladyhappy.settings.presentation.adapter.LoginAdapter
import com.egoriku.ladyhappy.settings.presentation.adapter.SettingItemAdapter
import com.egoriku.ladyhappy.settings.presentation.dialog.theme.ThemeSettingDialogFragment
import com.egoriku.ladyhappy.settings.presentation.screen.LoginScreen
import com.egoriku.ladyhappy.settings.presentation.screen.UsedLibrariesScreen
import com.egoriku.ladyhappy.settings.presentation.view.State.ANON
import com.egoriku.ladyhappy.settings.presentation.view.State.LOGGED_IN
import com.egoriku.ladyhappy.settings.presentation.viewmodel.ReviewViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import kotlin.properties.Delegates

class SettingFragment : Fragment(R.layout.fragment_settings), SettingsFeature {

    private val binding: FragmentSettingsBinding by viewBinding()

    private val featureProvider: IFeatureProvider by inject()

    private val reviewViewModel: ReviewViewModel by lifecycleScope.viewModel(this)
    private val settingsViewModel: SettingsViewModel by lifecycleScope.viewModel(this)

    private var concatAdapter: ConcatAdapter by Delegates.notNull()

    private var loginAdapter: LoginAdapter by Delegates.notNull()
    private var availableFeaturesAdapter: AvailableFeaturesAdapter by Delegates.notNull()
    private var settingsAdapter: SettingItemAdapter by Delegates.notNull()

    private var dynamicFeatureConnector: IDynamicFeatureConnector? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dynamicFeatureConnector = activity as IDynamicFeatureConnector
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginAdapter = LoginAdapter {
            when (it) {
                ANON -> settingsViewModel.navigateTo(LoginScreen(featureProvider), R.id.contentFullScreen)
                LOGGED_IN -> settingsViewModel.logOut()
            }
        }

        availableFeaturesAdapter = AvailableFeaturesAdapter {
            when (it) {
                is Feature.PublishPosts -> {
                    dynamicFeatureConnector?.installDynamicFeature(getString(R.string.title_post_creator))
                }
            }
        }

        settingsAdapter = SettingItemAdapter {
            when (it) {
                is SettingItem.Theme -> ThemeSettingDialogFragment().show(childFragmentManager, null)
                is SettingItem.UsedLibraries -> {
                    settingsViewModel.navigateTo(UsedLibrariesScreen(featureProvider), R.id.contentFullScreen)
                }
                is SettingItem.Review -> {
                    reviewViewModel.startReview { reviewInfo, reviewManager ->
                        reviewManager.launchReviewFlow(requireActivity(), reviewInfo)
                                .addOnSuccessListener {
                                    toast("Thanks for the feedback!")
                                }
                                .addOnFailureListener { it ->
                                    toast("Failed: ${it.message}")
                                }
                                .addOnCompleteListener {
                                    toast("Completed")
                                }
                    }
                }
            }
        }

        concatAdapter = ConcatAdapter(loginAdapter, availableFeaturesAdapter, settingsAdapter)

        binding.initViews()

        settingsViewModel.screenData.observe(owner = viewLifecycleOwner) {
            it.forEach { (_, section) ->
                when (section) {
                    is Section.Login -> loginAdapter.submitList(listOf(section))
                    is Section.AvailableFeatures -> availableFeaturesAdapter.submitList(listOf(section))
                    is Section.Settings -> settingsAdapter.submitList(section.setting)
                }
            }
        }

        reviewViewModel.preWarmReview()
    }

    override fun onDetach() {
        super.onDetach()
        dynamicFeatureConnector = null
    }

    private fun FragmentSettingsBinding.initViews() {
        settingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = concatAdapter
        }
    }
}