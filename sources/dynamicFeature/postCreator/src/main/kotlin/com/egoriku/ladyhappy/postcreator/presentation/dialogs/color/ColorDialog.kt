package com.egoriku.ladyhappy.postcreator.presentation.dialogs.color

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.updatePadding
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.getDimen
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.domain.dialog.DialogResult
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.presentation.KEY_CHOOSER_FRAGMENT_RESULT
import com.egoriku.ladyhappy.postcreator.presentation.KEY_FRAGMENT_RESULT_BUNDLE
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.color.adapter.ColorAdapter
import com.egoriku.ladyhappy.postcreator.presentation.dialogs.color.adapter.MyItemDetailsLookup
import com.egoriku.ladyhappy.ui.dialog.BaseDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.egoriku.ladyhappy.localization.R as R_localization

class ColorDialog : BaseDialogFragment() {

    private val colorAdapter = ColorAdapter()

    private var selectionTracker: SelectionTracker<Long>? = null

    override val dialogTitleResId: Int = R_localization.string.post_creator_color_dialog_title

    override fun onBuildDialog(
            builder: MaterialAlertDialogBuilder,
            savedInstanceState: Bundle?,
    ): MaterialAlertDialogBuilder {
        val recyclerView = RecyclerView(requireContext()).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = colorAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
            updatePadding(top = getDimen(R.dimen.color_dialog_padding))
        }

        selectionTracker = SelectionTracker.Builder(
                "colorSelection",
                recyclerView,
                StableIdKeyProvider(recyclerView),
                MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
                SelectionPredicates.createSelectAnything()
        ).build()

        colorAdapter.tracker = selectionTracker
        colorAdapter.submitList(PredefinedData.colors)

        selectionTracker?.onRestoreInstanceState(savedInstanceState)

        return builder.setView(recyclerView)
    }

    override fun onPositiveButtonClick() {
        val selectionTracker = checkNotNull(selectionTracker) {
            "Selection tracker null"
        }

        val colorIds = selectionTracker.selection
                .iterator()
                .asSequence()
                .map { position -> colorAdapter.currentList[position.toInt()] }
                .toList()

        setFragmentResult(
                KEY_CHOOSER_FRAGMENT_RESULT,
                bundleOf(KEY_FRAGMENT_RESULT_BUNDLE to DialogResult.Color(colorIds = colorIds))
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectionTracker?.onSaveInstanceState(outState)
    }
}