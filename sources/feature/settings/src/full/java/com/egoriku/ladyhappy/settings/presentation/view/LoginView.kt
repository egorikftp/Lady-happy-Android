package com.egoriku.ladyhappy.settings.presentation.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.egoriku.ladyhappy.extensions.getString
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.ViewLoginBinding
import com.egoriku.ladyhappy.settings.presentation.view.State.ANON
import com.egoriku.ladyhappy.settings.presentation.view.State.LOGGED_IN

class LoginView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding = ViewLoginBinding.inflate(inflater(), this)

    var state: State = ANON
        set(value) {
            field = value

            setState()
        }

    init {
        if (isInEditMode) {
            state = LOGGED_IN
        }
    }

    private fun setState() = when (state) {
        LOGGED_IN -> {
            with(binding) {
                loginHint.gone()
                loginButton.text = getString(R.string.settings_log_out)
                userName.visible()
            }
        }

        ANON -> {
            with(binding) {
                userName.gone()
                loginHint.visible()
                loginButton.text = getString(R.string.settings_login)
            }
        }
        else -> {
        }
    }

    fun setProfileImage(drawable: Drawable) {
        binding.profileImage.setImageDrawable(drawable)
    }

    fun onButtonClick(block: () -> Unit) {
        binding.loginButton.setOnClickListener {
            block()
        }
    }

    fun setUserName(name: String) {
        binding.userName.text = name
    }
}

enum class State {
    LOGGED_IN, ANON
}