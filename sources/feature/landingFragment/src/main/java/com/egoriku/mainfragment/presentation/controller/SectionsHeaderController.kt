package com.egoriku.mainfragment.presentation.controller

import android.support.annotation.StringRes
import android.view.View
import android.view.ViewGroup
import com.egoriku.mainfragment.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_section_header.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class SectionsHeaderController : BindableItemController<Int, SectionsHeaderController.Holder>() {

    override fun getItemId(data: Int) = data.hashCode().toString()

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BindableViewHolder<Int>(parent, R.layout.adapter_item_section_header), LayoutContainer {

        override val containerView: View?
            get() = itemView

        override fun bind(@StringRes stringRes: Int) {
            sectionHeader.text = itemView.context.getString(stringRes)
        }
    }
}