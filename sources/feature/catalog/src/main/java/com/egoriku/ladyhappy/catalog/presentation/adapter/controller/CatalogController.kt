package com.egoriku.ladyhappy.catalog.presentation.adapter.controller

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.catalog.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_catalog.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class CatalogController : BindableItemController<String, CatalogController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_catalog), LayoutContainer {

        override val containerView: View = itemView

        override fun bind(data: String) {
            textView.text = "Широкополая шляпка"

            listOf(
                    imageView,
                    imageView2,
                    imageView3,
                    imageView4,
                    imageView5,
                    imageView6,
                    imageView7
            ).forEach {
                Glide.with(itemView.context)
                        .load("https://lady-happy.com/assets/images/portfolio/5.jpg")
                        .into(it)
            }
        }
    }
}