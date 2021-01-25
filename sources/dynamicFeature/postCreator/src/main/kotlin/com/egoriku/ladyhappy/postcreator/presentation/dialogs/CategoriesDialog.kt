package com.egoriku.ladyhappy.postcreator.presentation.dialogs

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.egoriku.ladyhappy.extensions.dataBySelectedPosition
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.presentation.KEY_CHOOSER_FRAGMENT_RESULT
import com.egoriku.ladyhappy.postcreator.presentation.KEY_FRAGMENT_RESULT_BUNDLE
import com.egoriku.ladyhappy.ui.dialog.BaseDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.egoriku.ladyhappy.localization.R as R_localization

class CategoriesDialog : BaseDialogFragment() {

    private var selectedItemPosition = -1

    override val dialogTitleResId: Int = R_localization.string.post_creator_categories_dialog_title

    override fun onBuildDialog(
            builder: MaterialAlertDialogBuilder,
            savedInstanceState: Bundle?
    ): MaterialAlertDialogBuilder =
            builder.setSingleChoiceItems(getCategories(requireArguments()), 0) { _, which ->
                selectedItemPosition = which
            }

    override fun onPositiveButtonClick() {
        val checkedItemName = dataBySelectedPosition<String>()

        setFragmentResult(
                KEY_CHOOSER_FRAGMENT_RESULT,
                bundleOf(KEY_FRAGMENT_RESULT_BUNDLE to DialogResult.Category(checkedItemName))
        )
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