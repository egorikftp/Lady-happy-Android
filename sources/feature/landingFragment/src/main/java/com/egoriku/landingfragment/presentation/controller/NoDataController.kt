package com.egoriku.landingfragment.presentation.controller

import android.view.View
import android.view.ViewGroup
import com.egoriku.landingfragment.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_no_data.*
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

internal class NoDataController(val retryListener: () -> Unit) : NoDataItemController<NoDataController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_item_no_data), LayoutContainer {
        override val containerView: View?
            get() = itemView

        init {
            retryButton.setOnClickListener { retryListener.invoke() }
        }
    }
}