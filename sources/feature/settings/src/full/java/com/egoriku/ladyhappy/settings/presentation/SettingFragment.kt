package com.egoriku.ladyhappy.settings.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.extensions.colorCompat
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.extensions.viewBindingLifecycle
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.FragmentSettingsBinding
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

class SettingFragment : Fragment() {

    private var binding: FragmentSettingsBinding by viewBindingLifecycle()

    private val viewModel: SettingsViewModel by lifecycleScope.viewModel(this)
    private val featureProvider: IFeatureProvider by inject()

    private var mergeAdapter: MergeAdapter by Delegates.notNull()

    private var loginAdapter: LoginAdapter by Delegates.notNull()
    private var availableFeaturesAdapter: AvailableFeaturesAdapter by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentSettingsBinding.inflate(inflater, container, false).apply {
            binding = this
            return root
        }
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
            toast(it.toString())
        }

        mergeAdapter = MergeAdapter(loginAdapter, availableFeaturesAdapter)

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