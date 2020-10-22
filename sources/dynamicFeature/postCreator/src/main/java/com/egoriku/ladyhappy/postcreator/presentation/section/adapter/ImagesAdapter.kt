package com.egoriku.ladyhappy.postcreator.presentation.section.adapter

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.egoriku.extensions.colorFromAttr
import com.egoriku.extensions.inflater
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemImageBinding
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ui.R as R_ui

class ImagesAdapter(
        private val onRemoveImageClick: (item: ImageItem) -> Unit
) : ListAdapter<ImageItem, ImagesAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(AdapterItemImageBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    inner class VH(private val binding: AdapterItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        private val placeholderDrawable = ColorDrawable(itemView.colorFromAttr(R_ui.attr.colorPlaceholder))

        init {
            binding.removeImage.setOnClickListener {
                onRemoveImageClick(getItem(bindingAdapterPosition))
            }
        }

        fun bind(item: ImageItem) {
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