package com.egoriku.ladyhappy.extensions

import android.text.Editable
import android.util.Patterns

fun String.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
