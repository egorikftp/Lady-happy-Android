package com.egoriku.ladyhappy.postcreator.presentation.sections.images

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.RoundedCornersTransformation
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemImageBinding
import com.egoriku.ladyhappy.postcreator.domain.model.image.ImageItem
import com.egoriku.ladyhappy.ui.R as R_ui

class ImagesAdapter(
    private val onRemoveImageClick: (item: ImageItem) -> Unit
) : BaseListAdapter<ImageItem, ImagesAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = VH(AdapterItemImageBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
        holder: VH,
        position: Int,
        model: ImageItem
    ) = holder.bind(model)

    inner class VH(
        private val binding: AdapterItemImageBinding
    ) : BaseViewHolder<ImageItem>(binding.root) {

        private val placeholderDrawable =
            ColorDrawable(itemView.colorFromAttr(R_ui.attr.colorPlaceholder))

        init {
            binding.removeImage.setOnClickListener {
                onRemoveImageClick(getItem(bindingAdapterPosition))
            }
        }

        override fun bind(item: ImageItem) {
            binding.attachmentImage.load(uri = item.uri) {
                placeholder(placeholderDrawable)
                transformations(RoundedCornersTransformation(radius = 16f))
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<ImageItem>() {

        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
            newItem.uri == oldItem.uri
    }
}