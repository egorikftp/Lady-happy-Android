package com.egoriku.ladyhappy.postcreator.presentation.dialogs.color.adapter

import android.content.res.ColorStateList
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.widget.CompoundButtonCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.adjustForBackground
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemColorBinding
import com.egoriku.ladyhappy.postcreator.domain.predefined.ColorModel

class ColorAdapter : ListAdapter<ColorModel, ColorAdapter.VH>(DiffCallback()) {

    init {
        setHasStableIds(true)
    }

    var tracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            VH(AdapterItemColorBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        tracker?.run {
            holder.bind(getItem(position), isSelected(position.toLong()))
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.adapter_item_color

    override fun getItemId(position: Int): Long = position.toLong()

    class VH(private val binding: AdapterItemColorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(colorModel: ColorModel, isSelected: Boolean) = binding.bind(colorModel, isSelected)

        private fun AdapterItemColorBinding.bind(colorModel: ColorModel, isSelected: Boolean) {
            colorName.text = colorModel.name
            colorImage.setBackgroundColor(colorModel.colorHex.toColorInt())

            val adjustedColor = colorName.currentTextColor.adjustForBackground(backgroundColor = colorModel.colorHex.toColorInt())

            colorName.setTextColor(adjustedColor)
            CompoundButtonCompat.setButtonTintList(checkBox, ColorStateList.valueOf(adjustedColor))

            itemView.isActivated = isSelected
            binding.checkBox.isActivated = isSelected
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
                object : ItemDetailsLookup.ItemDetails<Long>() {
                    override fun getPosition(): Int = bindingAdapterPosition

                    override fun getSelectionKey(): Long = itemId

                    override fun inSelectionHotspot(e: MotionEvent) = true
                }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<ColorModel>() {

        override fun areItemsTheSame(oldItem: ColorModel, newItem: ColorModel) = oldItem == newItem

        override fun areContentsTheSame(oldItem: ColorModel, newItem: ColorModel) = oldItem == newItem
    }
}

class MyItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {

    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)

        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ColorAdapter.VH).getItemDetails()
        }

        return null
    }
}