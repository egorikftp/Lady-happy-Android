package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import ru.surfstudio.easyadapter.recycler.controller.NoDataItemController
import ru.surfstudio.easyadapter.recycler.holder.BaseViewHolder

class QuotesHeaderController : NoDataItemController<QuotesHeaderController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_item_quotes_header)
}