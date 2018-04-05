package com.egoriku.ladyhappy.presentation.fragment.main.conroller

import android.view.ViewGroup
import com.egoriku.ladyhappy.R
import kotlinx.android.synthetic.main.adapter_item_section_header.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class SectionsHeaderController : BindableItemController<String, SectionsHeaderController.Holder>() {

    override fun getItemId(data: String) = data.hashCode().toLong()

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_section_header) {

        private val sectionHeader = itemView.sectionHeader

        override fun bind(data: String) {
            sectionHeader.text = data
        }
    }
}