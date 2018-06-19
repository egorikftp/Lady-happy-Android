package com.egoriku.ladyhappy.presentation.fragment.allgoods.controller

import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import kotlinx.android.synthetic.main.adapter_item_error_state.view.*
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class ErrorStateController(val onReloadClickListener: () -> Unit)
    : NoDataItemController<ErrorStateController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_item_error_state) {

        init {
            itemView.retryButton.setOnClickListener { onReloadClickListener }
        }
    }
}