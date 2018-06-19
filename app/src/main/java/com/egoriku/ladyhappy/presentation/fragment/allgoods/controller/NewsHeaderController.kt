package com.egoriku.ladyhappy.presentation.fragment.allgoods.controller

import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class NewsHeaderController : NoDataItemController<NewsHeaderController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.adapter_item_news_header)
}