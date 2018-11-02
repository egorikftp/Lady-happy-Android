package com.egoriku.photoreportfragment.presentation.controller

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.egoriku.photoreportfragment.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_photo_report.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoReportItemController : BindableItemController<String, PhotoReportItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: String) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.adapter_item_photo_report), LayoutContainer {

        override val containerView: View?
            get() = itemView

        private val requestOptions = RequestOptions().placeholder(ContextCompat.getDrawable(itemView.context, R.color.RealBlack30))

        override fun bind(data: String) {
            Glide.with(itemView.context)
                    .load(data)
                    .transition(withCrossFade())
                    .apply(requestOptions)
                    .into(itemView.reportPhotoItem)
        }
    }
}