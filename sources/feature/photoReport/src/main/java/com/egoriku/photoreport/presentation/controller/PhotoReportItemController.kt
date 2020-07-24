package com.egoriku.photoreport.presentation.controller

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.egoriku.extensions.drawableCompat
import com.egoriku.extensions.inflater
import com.egoriku.photoreport.R
import com.egoriku.photoreport.databinding.AdapterItemPhotoReportBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoReportItemController : BindableItemController<String, PhotoReportItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemPhotoReportBinding.inflate(parent.inflater(), parent, false))

    override fun getItemId(data: String) = data.hashCode().toString()

    inner class Holder(
            private val itemBinding: AdapterItemPhotoReportBinding
    ) : BindableViewHolder<String>(itemBinding.root) {

        private val requestOptions = RequestOptions()
                .placeholder(itemView.drawableCompat(R.color.Placeholder))

        override fun bind(data: String) {
            Glide.with(itemView.context)
                    .load(data)
                    .transition(withCrossFade())
                    .apply(requestOptions)
                    .into(itemBinding.reportPhotoItem)
        }
    }
}