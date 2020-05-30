package com.egoriku.ladyhappy.postcreator.presentation.dialogs.listener

import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult

interface DialogValueChangeListener {

    fun onValueChanged(dialogResult: DialogResult)
}