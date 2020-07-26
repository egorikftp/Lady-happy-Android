package com.egoriku.ladyhappy.login.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.extensions.*
import com.egoriku.ladyhappy.login.R
import com.egoriku.ladyhappy.login.databinding.FragmentLoginBinding
import com.egoriku.ladyhappy.login.presentation.util.validateEmail
import com.egoriku.ladyhappy.login.presentation.util.validatePassword
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind()
    }

    private fun FragmentLoginBinding.bind() {
        closeView.setOnClickListener {
            viewModel.processBack()
        }

        forgotPassword.setOnClickListener {
            toast("will be implemented soon")
        }

        signInButton.setOnClickListener {
            val isValid = fun(): Boolean {
                return textInputEmail.validateEmail()
                        && textInputPassword.validatePassword()
            }

            if (isValid()) {
                viewModel.authWithEmailAndPassword(
                        email = loginEmail.text.toString(),
                        password = loginPassword.text.toString()
                )
            }

            hideSoftKeyboard()
        }

        termsOfServiceButton.setOnClickListener {
            initCustomTab(urlRes = R.string.terms_of_service_link)
        }

        privacyPolicyButton.setOnClickListener {
            initCustomTab(urlRes = R.string.privacy_policy_link)
        }

        viewModel.currentState.observe(viewLifecycleOwner) {
            when (it) {
                is LoginState.Progress -> {
                    parentProgress.visible()
                    contentLoadingProgressBar.show()
                }
                is LoginState.Success -> {
                    parentProgress.gone()
                    contentLoadingProgressBar.hide()

                    viewModel.processBack()
                }
                is LoginState.Error -> {
                    //TODO show error
                    parentProgress.gone()
                    contentLoadingProgressBar.hide()
                    toast("error ${it.message}")
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        hideSoftKeyboard()
    }

    private fun initCustomTab(@StringRes urlRes: Int) = CustomTabsIntent.Builder()
            .setToolbarColor(colorCompat(R.color.RoseTaupe))
            .build().run {
                launchUrl(requireContext(), getString(urlRes).toUri())
            }
}