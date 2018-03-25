package com.egoriku.ladyhappy.presentation.fragment.allgoods.controller

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.egoriku.ladyhappy.R
import kotlinx.android.synthetic.main.adapter_item_news_image.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class NewsImagesController : BindableItemController<String, NewsImagesController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_news_image) {

        private val newsImage: ImageView = itemView.newsImageView

        override fun bind(data: String) {
            Glide.with(itemView.context)
                    .load(data)
                    .transition(withCrossFade())
                    .into(newsImage)
        }
    }
}