package com.egoriku.ladyhappy.postcreator.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.egoriku.ladyhappy.extensions.drawableCompat
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemImageBinding
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ui.R as R_ui

class ImagesAdapter(
        private val onAddImageClick: (item: ImageItem) -> Unit
) : ListAdapter<ImageItem, ImagesAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(AdapterItemImageBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    inner class VH(private val binding: AdapterItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.removeImage.setOnClickListener {
                onAddImageClick(getItem(bindingAdapterPosition))
            }
        }

        fun bind(item: ImageItem) {
            val context = binding.root.context

            Glide.with(context)
                    .load(item.uri)
                    .placeholder(context.drawableCompat(R_ui.color.Placeholder))
                    .apply(RequestOptions()
                            .transform(
                                    CenterCrop(),
                                    RoundedCorners(16)
                            ))
                    .into(binding.attachmentImage)
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<ImageItem>() {

        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
                oldItem == newItem

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
                newItem.uri == oldItem.uri
    }
}