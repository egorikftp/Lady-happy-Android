package com.egoriku.landingfragment.presentation.controller

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.egoriku.landingfragment.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_about.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class AboutController : BindableItemController<String, AboutController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_about), LayoutContainer {

        override val containerView: View
            get() = itemView

        override fun bind(data: String) {
            (itemView.about_content as TextView).text = data
        }
    }
}
