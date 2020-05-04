package com.egoriku.ladyhappy.postcreator.presentation.dialogs

import android.content.Context
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.domain.predefined.CategoryModel
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.listener.DialogValueChangeListener
import com.egoriku.ui.dialog.BaseDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.egoriku.ladyhappy.localization.R as R_localization

class CategoriesDialog : BaseDialogFragment() {

    private var listener: DialogValueChangeListener? = null

    private var selectedItemPosition = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = parentFragment as DialogValueChangeListener
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    override val dialogTitleResId: Int = R_localization.string.post_creator_categories_dialog_title

    override fun onBuildDialog(builder: MaterialAlertDialogBuilder) =
            builder.setSingleChoiceItems(getCategories(requireArguments()), 0) { _, which ->
                selectedItemPosition = which
            }

    override fun onPositiveButtonClick() {
        super.onPositiveButtonClick()

        val lw: ListView = (dialog as AlertDialog).listView
        val checkedItem: Any = lw.adapter.getItem(lw.checkedItemPosition)

        listener?.onValueChanged(DialogResult.Categories(CategoryModel("kek", emptyList())))

        toast("positive")
    }

    companion object {
        private const val EXTRA_CATEGORIES = "EXTRA_CATEGORIES"

        private fun getCategories(bundle: Bundle) = bundle.getStringArrayList(EXTRA_CATEGORIES)?.toTypedArray()
                ?: throw IllegalArgumentException()

        fun newInstance(categoriesName: ArrayList<String>) = CategoriesDialog().apply {
            arguments = Bundle().apply {
                putStringArrayList(EXTRA_CATEGORIES, categoriesName)
            }
        }
    }
}