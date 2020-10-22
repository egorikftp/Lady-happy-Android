package com.egoriku.ladyhappy.settings.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.context
import com.egoriku.ladyhappy.extensions.drawableCompat
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.AdapterItemFeatureBinding
import com.egoriku.ladyhappy.settings.domain.model.Feature

class FeatureAdapter(
        private val onFeatureClick: (feature: Feature) -> Unit
) : ListAdapter<Feature, FeatureAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(AdapterItemFeatureBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: FeatureAdapter.VH, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int) = R.layout.adapter_item_feature

    inner class VH(private val binding: AdapterItemFeatureBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Feature) = binding.bind(model)

        private fun AdapterItemFeatureBinding.bind(feature: Feature) {
            when (feature) {
                is Feature.Stub -> root.setOnClickListener(null)
                else -> root.setOnClickListener {
                    onFeatureClick(feature)
                }
            }

            featureLogo.setImageDrawable(context.drawableCompat(feature.iconResId))
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<Feature>() {

        override fun areItemsTheSame(oldItem: Feature, newItem: Feature) =
                oldItem.isAvailable == newItem.isAvailable

        override fun areContentsTheSame(oldItem: Feature, newItem: Feature) =
                oldItem == newItem
    }
}