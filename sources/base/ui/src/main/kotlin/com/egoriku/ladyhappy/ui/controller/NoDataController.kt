package com.egoriku.ladyhappy.ui.controller

import android.view.ViewGroup
import com.egoriku.extensions.inflater
import com.egoriku.ladyhappy.ui.databinding.AdapterItemNoDataBinding
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class NoDataController(
        private val retryListener: () -> Unit
) : NoDataItemController<NoDataController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemNoDataBinding.inflate(parent.inflater(), parent, false))

    inner class Holder(
            itemBinding: AdapterItemNoDataBinding
    ) : BaseViewHolder(itemBinding.root) {

        init {
            itemBinding.retryButton.setOnClickListener { retryListener() }
        }
    }
}