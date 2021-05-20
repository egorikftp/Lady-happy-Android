package com.egoriku.ladyhappy.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseDialogFragment : AppCompatDialogFragment(),
    DialogInterface.OnClickListener {

    abstract val dialogTitleResId: Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
            .setTitle(dialogTitleResId)

        return onBuildDialog(builder, savedInstanceState)
            .setOnKeyListener { _, keyCode, event ->
                return@setOnKeyListener keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP
            }
            .setPositiveButton(android.R.string.ok, this)
            .setNegativeButton(android.R.string.cancel, this)
            .create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> onPositiveButtonClick()
            DialogInterface.BUTTON_NEGATIVE -> onNegativeButtonClick()
        }
    }

    abstract fun onBuildDialog(
        builder: MaterialAlertDialogBuilder,
        savedInstanceState: Bundle?
    ): MaterialAlertDialogBuilder

    open fun onPositiveButtonClick() {}

    open fun onNegativeButtonClick() {}
}
