package com.egoriku.ladyhappy.postcreator.presentation.section.adapter

import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemAddImageBinding
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem

class AddImageAdapter(
        private val onAddImageClick: () -> Unit
) : RecyclerView.Adapter<AddImageAdapter.VH>() {

    private val list = listOf(ImageItem(EMPTY.toUri()))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(AdapterItemAddImageBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = Unit

    inner class VH(binding: AdapterItemAddImageBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onAddImageClick()
            }
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.adapter_item_add_image

    override fun getItemCount() = list.size
}