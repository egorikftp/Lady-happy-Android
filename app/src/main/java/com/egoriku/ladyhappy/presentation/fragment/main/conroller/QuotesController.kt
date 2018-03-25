package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import kotlinx.android.synthetic.main.adapter_item_quotes.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class QuotesController : BindableItemController<String, QuotesController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_quotes) {

        private val quotaContent = itemView.quotesContent
        private val quotaAuthor = itemView.quotesAuthor

        override fun bind(data: String) {
            quotaContent.text = data
            quotaAuthor.text = "Ив Сен Лоран"
        }
    }
}
