package com.egoriku.ladyhappy.postcreator.presentation.section

import android.graphics.Typeface
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.egoriku.extensions.colorFromAttr
import com.egoriku.extensions.colorStateListCompat
import com.egoriku.extensions.inflater
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemImagesSectionBinding
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.presentation.model.ImageSection
import com.egoriku.ladyhappy.postcreator.presentation.section.adapter.AddImageAdapter
import com.egoriku.ladyhappy.postcreator.presentation.section.adapter.ImagesAdapter
import com.egoriku.ui.decorator.VerticalMarginItemDecoration
import com.egoriku.ladyhappy.localization.R as R_localization
import com.egoriku.ui.R as R_ui

private const val MAX_IMAGES_SIZE = 10

class ImagesSectionAdapter(
        private val chooseImage: () -> Unit,
        private val removeImage: (item: ImageItem) -> Unit
) : ListAdapter<ImageSection, ImagesSectionAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            VH(AdapterItemImagesSectionBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = R.layout.adapter_item_images_section

    inner class VH(private val binding: AdapterItemImagesSectionBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imagesAdapter = ImagesAdapter {
            removeImage(it)
        }

        private val concatAdapter = ConcatAdapter()

        init {
            concatAdapter.addAdapter(AddImageAdapter { chooseImage() })
            concatAdapter.addAdapter(imagesAdapter)

            binding.imagesRecycler.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = concatAdapter
                addItemDecoration(VerticalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.posts_images_margin)))
            }
        }

        fun bind(item: ImageSection) {
            val images = item.images

            imagesAdapter.submitList(images)

            binding.updateImagesCount(images)
        }

        private fun AdapterItemImagesSectionBinding.updateImagesCount(list: List<ImageItem>) {
            val size = list.size
            val context = root.context

            if (size > MAX_IMAGES_SIZE) {
                postImagesCount.setTextColor(context.colorFromAttr(R_ui.attr.colorPrimary))
                postImagesCount.setTypeface(null, Typeface.BOLD)

                icon.imageTintList = context.colorStateListCompat(R_ui.color.RoseTaupe)
            } else {
                postImagesCount.setTextColor(context.colorFromAttr(R_ui.attr.colorOnSurface))
                postImagesCount.setTypeface(null, Typeface.NORMAL)

                icon.imageTintList = context.colorStateListCompat(R_ui.color.RealBlack)
            }

            postImagesCount.text = String.format(context.getString(R_localization.string.post_creator_images_count), list.size)
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<ImageSection>() {

        override fun areItemsTheSame(oldItem: ImageSection, newItem: ImageSection): Boolean =
                newItem.images == oldItem.images

        override fun areContentsTheSame(oldItem: ImageSection, newItem: ImageSection): Boolean =
                newItem.images.size == oldItem.images.size
    }
}