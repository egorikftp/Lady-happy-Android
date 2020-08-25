package com.egoriku.ladyhappy.settings.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.extensions.inflater
import com.egoriku.ladyhappy.settings.R
import com.egoriku.ladyhappy.settings.databinding.AdapterItemFeaturesBinding
import com.egoriku.ladyhappy.settings.domain.model.Feature
import com.egoriku.ladyhappy.settings.domain.model.Section
import com.egoriku.ladyhappy.settings.presentation.adapter.decorator.MarginItemDecoration

class AvailableFeaturesSectionAdapter(
        private val onFeatureClick: (feature: Feature) -> Unit
) : ListAdapter<Section.AvailableFeatures, AvailableFeaturesSectionAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(AdapterItemFeaturesBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: AvailableFeaturesSectionAdapter.VH, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int) = R.layout.adapter_item_features

    inner class VH(private val binding: AdapterItemFeaturesBinding) : RecyclerView.ViewHolder(binding.root) {

        private val featureAdapter = FeatureAdapter {
            onFeatureClick(it)
        }

        init {
            binding.featuresRecyclerView.apply {
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = featureAdapter
                addItemDecoration(
                        MarginItemDecoration(
                                binding.root.context.resources.getDimensionPixelOffset(R.dimen.material_padding_16))
                )
            }
        }

        fun bind(section: Section.AvailableFeatures) = binding.bind(section)

        private fun AdapterItemFeaturesBinding.bind(section: Section.AvailableFeatures) {
            (featuresRecyclerView.adapter as FeatureAdapter).submitList(section.features)
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<Section.AvailableFeatures>() {

        override fun areItemsTheSame(
                oldItem: Section.AvailableFeatures,
                newItem: Section.AvailableFeatures
        ) = oldItem.features.size == newItem.features.size

        override fun areContentsTheSame(
                oldItem: Section.AvailableFeatures,
                newItem: Section.AvailableFeatures
        ) = oldItem.features == newItem.features
    }
}