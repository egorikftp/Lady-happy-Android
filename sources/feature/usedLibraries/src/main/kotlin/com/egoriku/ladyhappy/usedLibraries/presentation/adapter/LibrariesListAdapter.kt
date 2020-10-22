package com.egoriku.ladyhappy.usedLibraries.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.usedLibraries.R
import com.egoriku.ladyhappy.usedLibraries.databinding.AdapterItemLibraryBinding
import com.egoriku.ladyhappy.usedLibraries.domain.model.License

class LibrariesListAdapter(
        private val onItemClick: (license: String) -> Unit
) : ListAdapter<License, LibrariesListAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            VH(AdapterItemLibraryBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = R.layout.adapter_item_library

    inner class VH(private val binding: AdapterItemLibraryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: License) {
            binding.bind(item)
        }

        private fun AdapterItemLibraryBinding.bind(item: License) {
            text.text = item.libraryName
            itemLibraryRoot.setOnClickListener {
                onItemClick(item.libraryLicense)
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<License>() {

        override fun areItemsTheSame(oldItem: License, newItem: License) = oldItem == newItem

        override fun areContentsTheSame(oldItem: License, newItem: License) = oldItem.libraryName == newItem.libraryName
    }
}