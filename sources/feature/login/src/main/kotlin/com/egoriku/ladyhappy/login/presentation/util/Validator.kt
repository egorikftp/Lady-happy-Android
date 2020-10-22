package com.egoriku.ladyhappy.login.presentation.util

import com.egoriku.ladyhappy.extensions.isEmailValid
import com.egoriku.ladyhappy.login.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.validateEmail(): Boolean {
    val editText = editText ?: throw IllegalArgumentException("EditText in TextInputLayout mustn't be null")
    val emailText = editText.text.toString()

    error = null

    return when {
        emailText.isEmpty() -> {
            error = context.getString(R.string.login_error_email_empty)
            false
        }
        !emailText.isEmailValid() -> {
            error = context.getString(R.string.login_error_email_invalid)
            false
        }
        else -> true
    }
}

fun TextInputLayout.validatePassword(): Boolean {
    val editText = editText ?: throw IllegalArgumentException("EditText in TextInputLayout mustn't be null")
    val passwordText = editText.text.toString()

    error = null

    return when {
        passwordText.isEmpty() -> {
            error = context.getString(R.string.login_error_password_empty)
            false
        }
        else -> true
    }
}