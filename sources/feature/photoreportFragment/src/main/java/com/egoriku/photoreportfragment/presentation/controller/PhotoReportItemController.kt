package com.egoriku.photoreportfragment.presentation.controller

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.egoriku.photoreportfragment.R
import kotlinx.android.synthetic.main.adapter_item_photo_report.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoReportItemController : BindableItemController<String, PhotoReportItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_photo_report) {

        private val newsImage: ImageView = itemView.newsImageView

        override fun bind(data: String) {
            Glide.with(itemView.context)
                    .load(data)
                    .transition(withCrossFade())
                    .into(newsImage)
        }
    }
}