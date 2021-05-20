package com.egoriku.ladyhappy.postcreator.presentation.sections.chooser

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.extensions.context
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemChooserSectionBinding
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType.ChooserState

class ChooserSectionAdapter(
    private val chooserItemClick: (chooserState: ChooserType) -> Unit,
    private val resetItemClick: (chooserState: ChooserType) -> Unit,
) : BaseListAdapter<ChooserType, ChooserSectionAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VH = VH(AdapterItemChooserSectionBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
        holder: VH,
        position: Int,
        model: ChooserType,
    ) = holder.bind(model)

    inner class VH(
        private val binding: AdapterItemChooserSectionBinding,
    ) : BaseViewHolder<ChooserType>(binding.root) {

        override fun bind(item: ChooserType) = when (item.state) {
            is ChooserState.Initial -> binding.initial(item)
            is ChooserState.Selected -> binding.selected(item)
        }

        private fun AdapterItemChooserSectionBinding.initial(item: ChooserType) {
            chooser.hintText = context.getString(item.hintResId)
            chooser.reset()
            chooser.setOnClickListener {
                chooserItemClick(item)
            }
        }

        private fun AdapterItemChooserSectionBinding.selected(item: ChooserType) {
            chooser.setPrimary(text = item.title, hint = context.getString(item.hintResId))
            chooser.onClearIconClickListener = { resetItemClick(item) }
            chooser.setOnClickListener { chooserItemClick(item) }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<ChooserType>() {

        override fun areItemsTheSame(oldItem: ChooserType, newItem: ChooserType): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ChooserType, newItem: ChooserType): Boolean =
            newItem == oldItem
    }
}