package com.egoriku.ladyhappy.login.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.Annotation
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.extensions.*
import com.egoriku.ladyhappy.login.R
import com.egoriku.ladyhappy.login.databinding.FragmentLoginBinding
import com.egoriku.ladyhappy.login.presentation.util.ClickableSpan
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

        initSignUpSpannable {
            toast("will be implemented soon")
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

    private fun initSignUpSpannable(onSpanClick: () -> Unit) {
        val spannedString = getText(R.string.login_go_to_sign_up) as SpannedString

        spannedString.getSpans(0, spannedString.length, Annotation::class.java)
                .forEach { annotation ->
                    if (annotation.key == "clickColor") {
                        val builder = SpannableStringBuilder(spannedString).apply {
                            val spanStart = getSpanStart(annotation)
                            val spanEnd = getSpanEnd(annotation)

                            setSpan(object : ClickableSpan() {
                                override fun onClick(widget: View) = onSpanClick()

                                override fun updateDrawState(ds: TextPaint) {
                                    super.updateDrawState(ds)
                                    ds.apply {
                                        isUnderlineText = false
                                        typeface = Typeface.DEFAULT_BOLD
                                    }
                                }
                            }, spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                            setSpan(
                                    ForegroundColorSpan(colorCompat(findColorIdByName(annotation.value))),
                                    spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        }

                        with(binding.dontHaveAccount) {
                            text = builder
                            movementMethod = LinkMovementMethod.getInstance()
                        }
                    }
                }
    }
}