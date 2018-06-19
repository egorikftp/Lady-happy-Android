package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class HeaderController : NoDataItemController<HeaderController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_item_main_header)
}