package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.ViewGroup
import android.widget.TextView
import com.egoriku.ladyhappy.R
import kotlinx.android.synthetic.main.adapter_item_about.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class AboutController : BindableItemController<String, AboutController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_about) {

        private val aboutContent = itemView.about_content as TextView

        override fun bind(data: String) {
            aboutContent.text = data
        }
    }
}
