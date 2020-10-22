package com.egoriku.ladyhappy.postcreator.presentation.dialogs

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.egoriku.extensions.dataBySelectedPosition
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.presentation.fragment.BUNDLE_KEY
import com.egoriku.ladyhappy.postcreator.presentation.fragment.CHOOSER_KEY
import com.egoriku.ladyhappy.ui.dialog.BaseDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.egoriku.ladyhappy.localization.R as R_localization

class SubCategoriesDialog : BaseDialogFragment() {

    private var selectedItemPosition = -1

    override val dialogTitleResId: Int = R_localization.string.post_creator_subcategories_categories_dialog_title

    override fun onBuildDialog(
            builder: MaterialAlertDialogBuilder,
            savedInstanceState: Bundle?
    ): MaterialAlertDialogBuilder =
            builder.setSingleChoiceItems(getCategories(requireArguments()), 0) { _, which ->
                selectedItemPosition = which
            }

    override fun onPositiveButtonClick() {
        super.onPositiveButtonClick()

        val checkedItemName = dataBySelectedPosition<String>()

        setFragmentResult(CHOOSER_KEY, bundleOf(BUNDLE_KEY to DialogResult.SubCategory(checkedItemName)))
    }

    companion object {
        private const val EXTRA_SUB_CATEGORIES = "EXTRA_SUB_CATEGORIES"

        private fun getCategories(bundle: Bundle) = bundle.getStringArrayList(EXTRA_SUB_CATEGORIES)?.toTypedArray()
                ?: throw IllegalArgumentException()

        fun newInstance(subCategories: ArrayList<String>) = SubCategoriesDialog().apply {
            arguments = Bundle().apply {
                putStringArrayList(EXTRA_SUB_CATEGORIES, subCategories)
            }
        }
    }
}