package com.egoriku.photoreportfragment.presentation.controller

import android.view.View
import android.view.ViewGroup
import com.egoriku.photoreportfragment.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_error_state.*
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class ErrorStateController(val onReloadClickListener: () -> Unit) : NoDataItemController<ErrorStateController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_item_error_state), LayoutContainer {

        override val containerView: View?
            get() = itemView

        init {
            retryButton.setOnClickListener { onReloadClickListener }
        }
    }
}