package com.egoriku.ladyhappy.extensions

import androidx.appcompat.widget.SwitchCompat

inline fun SwitchCompat.setOnCheckedChangeListener(crossinline listener: (Boolean) -> Unit) {
    setOnCheckedChangeListener { buttonView, isChecked ->
        if (!buttonView.isPressed) {
            return@setOnCheckedChangeListener
        }

        listener(isChecked)
    }
}