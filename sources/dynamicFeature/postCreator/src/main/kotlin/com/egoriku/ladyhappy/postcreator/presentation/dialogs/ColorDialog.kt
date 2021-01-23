package com.egoriku.ladyhappy.postcreator.presentation.dialogs

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.domain.predefined.ColorModel
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.presentation.BUNDLE_KEY
import com.egoriku.ladyhappy.postcreator.presentation.CHOOSER_KEY
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.color.ColorAdapter
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.color.MyItemDetailsLookup
import com.egoriku.ladyhappy.ui.dialog.BaseDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.egoriku.ladyhappy.localization.R as R_localization

class ColorDialog : BaseDialogFragment() {

    private val colorAdapter = ColorAdapter()

    private var selectionTracker: SelectionTracker<Long>? = null

    override val dialogTitleResId: Int = R_localization.string.post_creator_color_dialog_title

    override fun onBuildDialog(
            builder: MaterialAlertDialogBuilder,
            savedInstanceState: Bundle?
    ): MaterialAlertDialogBuilder {
        val recyclerView = RecyclerView(requireContext()).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = colorAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }

        selectionTracker = SelectionTracker.Builder(
                "colorSelection",
                recyclerView,
                StableIdKeyProvider(recyclerView),
                MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
                SelectionPredicates.createSelectSingleAnything()
        ).build()

        colorAdapter.tracker = selectionTracker
        colorAdapter.submitList(PredefinedData.colors)

        selectionTracker?.onRestoreInstanceState(savedInstanceState)

        return builder.setView(recyclerView)
    }

    override fun onPositiveButtonClick() {
        val selectionTracker = selectionTracker ?: throw Exception("Selection tracker null")
        val selectedPosition = selectionTracker.selection.iterator().next().toInt()
        val colorModel: ColorModel = colorAdapter.currentList[selectedPosition]

         setFragmentResult(
                 CHOOSER_KEY,
                 bundleOf(BUNDLE_KEY to DialogResult.Color(colorId = colorModel.colorId))
         )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectionTracker?.onSaveInstanceState(outState)
    }
}