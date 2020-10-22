package com.egoriku.ladyhappy.postcreator.presentation.section

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemChooserSectionBinding
import com.egoriku.ladyhappy.postcreator.presentation.model.Chooser

class ChooserSectionAdapter(
        private val chooserItemClick: (chooser: Chooser) -> Unit,
        private val resetItemClick: (chooser: Chooser.Selected) -> Unit
) : ListAdapter<Chooser, ChooserSectionAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            VH(AdapterItemChooserSectionBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    inner class VH(private val binding: AdapterItemChooserSectionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chooser) = when (item) {
            is Chooser.Initial -> binding.initial(item)
            is Chooser.Selected -> binding.selected(item)
        }

        private fun AdapterItemChooserSectionBinding.initial(item: Chooser.Initial) {
            chooser.hintText = item.type.hint
            chooser.reset()
            chooser.setOnClickListener {
                chooserItemClick(item)
            }
        }

        private fun AdapterItemChooserSectionBinding.selected(item: Chooser.Selected) {
            chooser.setPrimary(text = item.primary, hint = item.type.hint)
            chooser.onClearIconClickListener = {
                resetItemClick(item)
            }
            chooser.setOnClickListener {
                chooserItemClick(item)
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<Chooser>() {

        override fun areItemsTheSame(oldItem: Chooser, newItem: Chooser): Boolean =
                oldItem == newItem

        override fun areContentsTheSame(oldItem: Chooser, newItem: Chooser): Boolean =
                newItem == oldItem
    }
}