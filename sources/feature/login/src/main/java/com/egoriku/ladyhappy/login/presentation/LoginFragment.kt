package com.egoriku.ladyhappy.login.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.Annotation
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.extensions.*
import com.egoriku.ladyhappy.login.R
import com.egoriku.ladyhappy.login.databinding.FragmentLoginBinding
import com.egoriku.ladyhappy.login.presentation.util.ClickableSpan
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    //TODO move to ViewModel
    private val router: IRouter by inject()

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentLoginBinding.inflate(inflater, container, false).apply {
            binding = this

            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.elasticLayout.addListener {
            router.back()
        }

        binding.closeView.setOnClickListener {
            router.back()
        }

        binding.forgotPassword.setOnClickListener {
            toast("will be implemented soon")
        }

        binding.signInButton.setOnClickListener {
            viewModel.authWithEmailAndPassword(
                    email = "egorikftp@gmail.com",
                    password = "123456"
            )

            hideSoftKeyboard()
        }

        initSignUpSpannable {
            toast("will be implemented soon")
        }

        viewModel.currentState.observe(viewLifecycleOwner) {
            when (it) {
                is LoginState.Progress -> {
                    binding.parentProgress.visible()
                    binding.contentLoadingProgressBar.show()
                }
                is LoginState.Success -> {
                    binding.parentProgress.gone()
                    binding.contentLoadingProgressBar.hide()
                    router.back()
                }
                is LoginState.Error -> {
                    //TODO show error
                    binding.parentProgress.gone()
                    binding.contentLoadingProgressBar.hide()
                    toast("error ${it.message}")
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        binding.elasticLayout.removeListener()
        hideSoftKeyboard()
    }

    private fun initSignUpSpannable(onSpanClick: () -> Unit) {
        val spannedString = getText(R.string.go_to_sign_up) as SpannedString

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