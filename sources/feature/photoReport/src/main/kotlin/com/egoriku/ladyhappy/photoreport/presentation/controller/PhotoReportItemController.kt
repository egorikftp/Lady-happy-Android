package com.egoriku.ladyhappy.photoreport.presentation.controller

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import coil.load
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.photoreport.R
import com.egoriku.photoreport.databinding.AdapterItemPhotoReportBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoReportItemController : BindableItemController<String, PhotoReportItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemPhotoReportBinding.inflate(parent.inflater(), parent, false))

    override fun getItemId(data: String) = data.hashCode().toString()

    class Holder(
            private val itemBinding: AdapterItemPhotoReportBinding
    ) : BindableViewHolder<String>(itemBinding.root) {

        private val placeholderDrawable = ColorDrawable(itemView.colorFromAttr(R.attr.colorPlaceholder))

        override fun bind(data: String) {
            itemBinding.reportPhotoItem.load(uri = data) {
                placeholder(placeholderDrawable)
                crossfade(true)
            }
        }
    }
}