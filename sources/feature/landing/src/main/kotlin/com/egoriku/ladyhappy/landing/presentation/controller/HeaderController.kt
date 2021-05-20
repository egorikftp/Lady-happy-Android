package com.egoriku.ladyhappy.landing.presentation.controller

import android.view.ViewGroup
import com.egoriku.ladyhappy.landing.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

internal class HeaderController : NoDataItemController<HeaderController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) :
        BaseViewHolder(parent, R.layout.adapter_item_main_header)
}