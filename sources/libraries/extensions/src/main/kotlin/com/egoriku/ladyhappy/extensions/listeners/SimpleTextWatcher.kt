package com.egoriku.ladyhappy.extensions.listeners

import android.text.Editable
import android.text.TextWatcher

interface SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(
        charSequence: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) = Unit

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) =
        Unit
}