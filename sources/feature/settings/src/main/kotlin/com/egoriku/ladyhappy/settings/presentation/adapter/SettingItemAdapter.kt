package com.egoriku.ladyhappy.settings.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.extensions.inflater
import com.egoriku.extensions.visible
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.AdapterItemSettingBinding
import com.egoriku.ladyhappy.settings.domain.model.setting.SettingItem

internal class SettingItemAdapter(
        private val onItemClick: (settingItem: SettingItem) -> Unit
) : ListAdapter<SettingItem, SettingItemAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(AdapterItemSettingBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int) = R.layout.adapter_item_setting

    inner class VH(private val binding: AdapterItemSettingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: SettingItem) = binding.bind(model)

        private fun AdapterItemSettingBinding.bind(model: SettingItem) {
            when (model) {
                is SettingItem.Header -> {
                    header.visible()
                    header.setText(model.stringResource)
                }
                is SettingItem.NonClickable -> {
                    body.visible()
                    body.text = model.resource
                    body.isClickable = true
                }
                else -> {
                    body.visible()
                    body.setText(model.stringResource)
                    body.setOnClickListener {
                        onItemClick(model)
                    }
                }
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<SettingItem>() {

        override fun areItemsTheSame(oldItem: SettingItem, newItem: SettingItem) = oldItem == newItem

        override fun areContentsTheSame(oldItem: SettingItem, newItem: SettingItem) = oldItem == newItem
    }
}