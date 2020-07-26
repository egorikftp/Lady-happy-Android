package com.egoriku.ladyhappy.settings.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.core.IFeatureProvider
import com.egoriku.core.connector.IDynamicFeatureConnector
import com.egoriku.extensions.colorCompat
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.FragmentSettingsBinding
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.presentation.adapter.AvailableFeaturesAdapter
import com.egoriku.ladyhappy.settings.presentation.adapter.LoginAdapter
import com.egoriku.ladyhappy.settings.presentation.screen.LoginScreen
import com.egoriku.ladyhappy.settings.presentation.view.State.ANON
import com.egoriku.ladyhappy.settings.presentation.view.State.LOGGED_IN
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import kotlin.properties.Delegates

class SettingFragment : Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding()

    private val featureProvider: IFeatureProvider by inject()
    private val viewModel: SettingsViewModel by lifecycleScope.viewModel(this)

    private var mergeAdapter: ConcatAdapter by Delegates.notNull()

    private var loginAdapter: LoginAdapter by Delegates.notNull()
    private var availableFeaturesAdapter: AvailableFeaturesAdapter by Delegates.notNull()

    private var dynamicFeatureConnector: IDynamicFeatureConnector? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dynamicFeatureConnector = activity as IDynamicFeatureConnector
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginAdapter = LoginAdapter {
            when (it) {
                ANON -> viewModel.navigateTo(LoginScreen(featureProvider), R.id.contentFullScreen)
                LOGGED_IN -> viewModel.logOut()
            }
        }

        availableFeaturesAdapter = AvailableFeaturesAdapter {
            when (it) {
                is Feature.PublishPosts -> {
                    dynamicFeatureConnector?.installDynamicFeature(getString(R.string.title_post_creator))
                }
            }
        }

        mergeAdapter = ConcatAdapter(loginAdapter, availableFeaturesAdapter)

        binding.initViews()

        viewModel.screenData.observe(viewLifecycleOwner) {
            it.forEach { (_, section) ->
                when (section) {
                    is Section.Login -> loginAdapter.submitList(listOf(section))
                    is Section.AvailableFeatures -> availableFeaturesAdapter.submitList(listOf(section))
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        dynamicFeatureConnector = null
    }

    private fun FragmentSettingsBinding.initViews() {
        settingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mergeAdapter
        }

        applyBottomMargin()

        termsOfServiceButton.setOnClickListener {
            initCustomTab(urlRes = R.string.terms_of_service_link)
        }

        privacyPolicyButton.setOnClickListener {
            initCustomTab(urlRes = R.string.privacy_policy_link)
        }
    }

    private fun initCustomTab(@StringRes urlRes: Int) = CustomTabsIntent.Builder()
            .setToolbarColor(colorCompat(R.color.RoseTaupe))
            .build().run {
                launchUrl(requireContext(), getString(urlRes).toUri())
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