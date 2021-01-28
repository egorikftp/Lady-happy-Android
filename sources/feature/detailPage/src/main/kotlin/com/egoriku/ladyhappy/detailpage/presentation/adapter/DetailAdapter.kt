package com.egoriku.ladyhappy.detailpage.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.detailpage.databinding.AdapterItemDetailBinding
import com.egoriku.ladyhappy.extensions.inflater

class DetailAdapter : BaseListAdapter<String, DetailAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
    ) = VH(AdapterItemDetailBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
            holder: VH,
            position: Int,
            model: String,
    ) = holder.bind(model)

    inner class VH(
            private val binding: AdapterItemDetailBinding,
    ) : BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.text.text = item
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
}