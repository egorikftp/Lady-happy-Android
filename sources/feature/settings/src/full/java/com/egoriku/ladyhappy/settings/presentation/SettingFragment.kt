package com.egoriku.ladyhappy.settings.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.egoriku.core.feature.IFeatureProvider
import com.egoriku.ladyhappy.auth.model.UserLoginState
import com.egoriku.ladyhappy.extensions.colorCompat
import com.egoriku.ladyhappy.extensions.drawableCompat
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.FragmentSettingsBinding
import com.egoriku.ladyhappy.settings.presentation.screen.LoginScreen
import com.egoriku.ladyhappy.settings.presentation.view.State
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel: SettingsViewModel by viewModel()
    private val featureProvider: IFeatureProvider by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentSettingsBinding.inflate(inflater, container, false).apply {
            binding = this
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userLoginState.observe(viewLifecycleOwner) {
            when (it) {
                is UserLoginState.NotLoggedIn -> {
                    with(binding.loginView) {
                        setState(State.NOT_LOGGED_IN)
                        setProfileImage(drawableCompat(R.color.Terracota))
                        onButtonClick {
                            viewModel.navigateTo(
                                    LoginScreen(featureProvider), R.id.contentFullScreen
                            )
                        }
                    }
                }

                is UserLoginState.LoggedIn -> {
                    with(binding.loginView) {
                        setState(State.LOGGED_IN)
                        setProfileImage(drawableCompat(R.color.RoseTaupe))
                        setUserName(it.name)
                        onButtonClick {
                            viewModel.logOut()
                        }
                    }
                }
            }
        }

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