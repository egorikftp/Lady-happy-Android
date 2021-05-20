package com.egoriku.ladyhappy.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(private val height: Int = 0) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (position == state.itemCount - 1) {
            outRect.setEmpty()
        } else {
            outRect.bottom = height
        }
    }
}