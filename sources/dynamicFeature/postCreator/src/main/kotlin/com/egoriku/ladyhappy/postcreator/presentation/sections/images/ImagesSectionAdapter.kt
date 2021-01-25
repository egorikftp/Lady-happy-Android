package com.egoriku.ladyhappy.postcreator.presentation.sections.images

import android.graphics.Typeface
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.extensions.activated
import com.egoriku.ladyhappy.extensions.context
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.extensions.resetActivated
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.databinding.AdapterItemImagesSectionBinding
import com.egoriku.ladyhappy.postcreator.domain.model.image.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.model.image.ImageSection
import com.egoriku.ladyhappy.ui.decorator.VerticalMarginItemDecoration
import com.egoriku.ladyhappy.localization.R as R_localization

private const val MAX_IMAGES_SIZE = 10

class ImagesSectionAdapter(
        private val onChooseImage: () -> Unit,
        private val onRemoveImage: (item: ImageItem) -> Unit,
) : BaseListAdapter<ImageSection, ImagesSectionAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
    ) = VH(AdapterItemImagesSectionBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
            holder: VH,
            position: Int,
            model: ImageSection,
    ) = holder.bind(model)

    override fun getItemViewType(position: Int): Int = R.layout.adapter_item_images_section

    inner class VH(
            private val binding: AdapterItemImagesSectionBinding,
    ) : BaseViewHolder<ImageSection>(binding.root) {

        private val imagesAdapter = ImagesAdapter {
            onRemoveImage(it)
        }

        private val concatAdapter = ConcatAdapter()

        init {
            concatAdapter.addAdapter(AddImageAdapter { onChooseImage() })
            concatAdapter.addAdapter(imagesAdapter)

            binding.imagesRecycler.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = concatAdapter
                addItemDecoration(
                        VerticalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.posts_images_margin))
                )
            }
        }

        override fun bind(item: ImageSection) {
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