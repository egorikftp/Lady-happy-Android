package com.egoriku.landingfragment.presentation.controller

import android.view.ViewGroup
import com.egoriku.landingfragment.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

internal class ProgressController : NoDataItemController<ProgressController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_item_progress)
}