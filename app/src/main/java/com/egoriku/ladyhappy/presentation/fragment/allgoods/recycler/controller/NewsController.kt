package com.egoriku.ladyhappy.presentation.fragment.allgoods.recycler.controller

import android.view.ViewGroup
import android.widget.TextView
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.domain.models.SingleNewsModel
import kotlinx.android.synthetic.main.adapter_item_news.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class NewsController : BindableItemController<SingleNewsModel, NewsController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)
    override fun getItemId(data: SingleNewsModel) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<SingleNewsModel>(parent, R.layout.adapter_item_news) {

        private lateinit var category: SingleNewsModel
        private val title: TextView

        init {
            title = itemView.textView
        }

        override fun bind(data: SingleNewsModel) {
            category = data
            title.text = data.description
        }
    }
}