package com.egoriku.ladyhappy.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EmptySpaceItemDecoration(
        private val top: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) > 0) {
            outRect.top = top
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}