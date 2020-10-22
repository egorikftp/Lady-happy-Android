package com.egoriku.ladyhappy.extensions

import android.util.Patterns

fun String.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(this).matches()