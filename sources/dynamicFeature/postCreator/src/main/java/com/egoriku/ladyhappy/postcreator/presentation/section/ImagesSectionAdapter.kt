package com.egoriku.ladyhappy.postcreator.presentation.section

import android.graphics.Typeface
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.egoriku.extensions.activated
import com.egoriku.extensions.context
import com.egoriku.extensions.inflater
import com.egoriku.extensions.resetActivated
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemImagesSectionBinding
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.presentation.model.ImageSection
import com.egoriku.ladyhappy.postcreator.presentation.section.adapter.AddImageAdapter
import com.egoriku.ladyhappy.postcreator.presentation.section.adapter.ImagesAdapter
import com.egoriku.ui.decorator.VerticalMarginItemDecoration
import com.egoriku.ladyhappy.localization.R as R_localization

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
                addItemDecoration(
                        VerticalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.posts_images_margin))
                )
            }
        }

        fun bind(item: ImageSection) {
            val images = item.images

            imagesAdapter.submitList(images)

            binding.updateImagesCount(images)
        }

        private fun AdapterItemImagesSectionBinding.updateImagesCount(images: List<ImageItem>) {
            if (images.size > MAX_IMAGES_SIZE) {
                postImagesCount.activated()
                postImagesCount.setTypeface(null, Typeface.BOLD)

                icon.activated()
            } else {
                postImagesCount.resetActivated()
                postImagesCount.setTypeface(null, Typeface.NORMAL)

                icon.resetActivated()
            }

            postImagesCount.text = String.format(
                    context.getString(R_localization.string.post_creator_images_count),
                    images.size
            )
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<ImageSection>() {

        override fun areItemsTheSame(oldItem: ImageSection, newItem: ImageSection): Boolean =
                newItem.images == oldItem.images

        override fun areContentsTheSame(oldItem: ImageSection, newItem: ImageSection): Boolean =
                newItem.images.size == oldItem.images.size
    }
}