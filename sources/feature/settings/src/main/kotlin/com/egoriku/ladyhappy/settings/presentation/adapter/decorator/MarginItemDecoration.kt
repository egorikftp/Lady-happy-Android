package com.egoriku.ladyhappy.settings.presentation.adapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class MarginItemDecoration(
        private val marginSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        val adapterPosition = parent.getChildAdapterPosition(view)
        val adapterItemsCount = parent.adapter?.itemCount ?: 0

        outRect.top = marginSize

        when {
            adapterItemsCount > 1 && adapterPosition == adapterItemsCount - 1 -> outRect.right = marginSize
            adapterPosition > 0 -> outRect.right = marginSize
            adapterPosition == 0 -> {
                outRect.left = marginSize
                outRect.right = marginSize
            }
        }
    }
}