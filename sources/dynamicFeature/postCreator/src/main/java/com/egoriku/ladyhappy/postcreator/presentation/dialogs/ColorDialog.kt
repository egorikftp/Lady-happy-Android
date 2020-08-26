package com.egoriku.ladyhappy.postcreator.presentation.dialogs

import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.egoriku.extensions.inflater
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemColorBinding
import com.egoriku.ladyhappy.postcreator.domain.predefined.ColorModel
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ui.dialog.BaseDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.egoriku.ladyhappy.localization.R as R_localization

class ColorDialog : BaseDialogFragment() {

    private val colorAdapter = ColorAdapter()

    override val dialogTitleResId: Int = R_localization.string.post_creator_color_dialog_title

    override fun onBuildDialog(builder: MaterialAlertDialogBuilder): MaterialAlertDialogBuilder {
        val recyclerView = RecyclerView(requireContext()).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = colorAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }

        colorAdapter.submitList(PredefinedData.colors)

        return builder.setView(recyclerView)
    }
}

class ColorAdapter : ListAdapter<ColorModel, ColorAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            VH(AdapterItemColorBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = R.layout.adapter_item_color

    class VH(private val binding: AdapterItemColorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(colorModel: ColorModel) = binding.bind(colorModel)

        private fun AdapterItemColorBinding.bind(colorModel: ColorModel) {
            colorName.text = colorModel.name
            colorImage.background = (colorImage.background as GradientDrawable).apply {
                setColor(colorModel.color.toColorInt())
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<ColorModel>() {

        override fun areItemsTheSame(oldItem: ColorModel, newItem: ColorModel) = oldItem == newItem

        override fun areContentsTheSame(oldItem: ColorModel, newItem: ColorModel) = oldItem == newItem
    }
}