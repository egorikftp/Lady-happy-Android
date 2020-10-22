package com.egoriku.ladyhappy.login.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.extensions.*
import com.egoriku.ladyhappy.login.R
import com.egoriku.ladyhappy.login.databinding.FragmentLoginBinding
import com.egoriku.ladyhappy.login.presentation.state.LoginEvent
import com.egoriku.ladyhappy.login.presentation.state.LoginState
import com.egoriku.ladyhappy.login.presentation.util.validateEmail
import com.egoriku.ladyhappy.login.presentation.util.validatePassword
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel: LoginViewModel by viewModel()

    private val requestSignInWithGoogle = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken

            if (idToken != null) {
                viewModel.authWithToken(idToken)
            }
        } catch (e: ApiException) {
            logE(throwable = e)
        }
    }

    private val requestSignInWithOneTap = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
    ) {
        viewModel.processOneTapResult(it.data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind()

        viewModel.trySignIn()
    }

    private fun FragmentLoginBinding.bind() {
        closeView.setOnClickListener {
            viewModel.processBack()
        }

        forgotPassword.setOnClickListener {
            toast("will be implemented soon")
        }

        signInButton.setOnClickListener {
            if (binding.isInputsValid()) {
                viewModel.authWithEmailAndPassword(
                        email = loginEmail.text.toString(),
                        password = loginPassword.text.toString()
                )
            }

            hideSoftKeyboard()
        }

        signInButtonWithGoogle.setOnClickListener {
            viewModel.signWithGoogle()
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
                    parentProgress.gone()
                    contentLoadingProgressBar.hide()
                    toast("Error: ${it.message}")
                }
            }
        }

        viewModel.events.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is LoginEvent.OneTap -> {
                    requestSignInWithOneTap.launch(it.eventSenderRequest)
                }
                is LoginEvent.SignWithGoogle -> {
                    requestSignInWithGoogle.launch(it.signInIntent)
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        hideSoftKeyboard()
    }

    private fun FragmentLoginBinding.isInputsValid(): Boolean =
            textInputEmail.validateEmail() && textInputPassword.validatePassword()

    private fun initCustomTab(@StringRes urlRes: Int) = CustomTabsIntent.Builder()
            .setToolbarColor(colorCompat(R.color.RoseTaupe))
            .build().run {
                launchUrl(requireContext(), getString(urlRes).toUri())
            }
}