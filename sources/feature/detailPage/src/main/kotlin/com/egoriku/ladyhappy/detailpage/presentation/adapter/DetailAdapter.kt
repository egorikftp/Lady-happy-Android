package com.egoriku.ladyhappy.detailpage.presentation.adapter

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.detailpage.R
import com.egoriku.ladyhappy.detailpage.databinding.AdapterItemDetailBinding
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.context
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.mozaik.OnItemClick
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.ui.view.PhotoOverlayActions
import com.stfalcon.imageviewer.StfalconImageViewer

class DetailAdapter : BaseListAdapter<String, DetailAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
    ) = VH(AdapterItemDetailBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
            holder: VH,
            position: Int,
            model: String,
    ) = holder.bind(model)

    inner class VH(
            private val binding: AdapterItemDetailBinding,
    ) : BaseViewHolder<String>(binding.root) {

        private val colorDrawable = ColorDrawable(itemView.colorFromAttr(R.attr.colorPlaceholder))
        private val options = RequestOptions().centerCrop()

        //private val overlayViewBinding = ViewPhotoGalleryOverlayBinding.inflate(binding.inflater)

        private var stfalconImageViewer: StfalconImageViewer<MozaikItem>? = null

        init {
            binding.mozaikLayout.onViewReady = { view, url ->
                Glide.with(itemView.context)
                        .load(url)
                        .placeholder(colorDrawable)
                        .transition(DrawableTransitionOptions.withCrossFade(100))
                        .apply(options)
                        .into(view)
            }
        }

        override fun bind(item: String) = binding.bind(item)

        private fun AdapterItemDetailBinding.bind(item: String) {
            descriptionTextView.text = item
            dateTextView.text = "22 June 2020"

            mozaikLayout.setItems(listOf(
                    MozaikItem(1200, 800, "https://firebasestorage.googleapis.com/v0/b/lady-happy.appspot.com/o/subcategories%2F1.2%2FIMG_0207_2018.09.06_22-49%20(1).jpg?alt=media&token=b29b83cb-f2e8-4512-bd8a-b5cc120bd179"),
                    MozaikItem(1200, 800, "https://firebasestorage.googleapis.com/v0/b/lady-happy.appspot.com/o/subcategories%2F1.2%2FIMG_0207_2018.09.06_22-49%20(1).jpg?alt=media&token=b29b83cb-f2e8-4512-bd8a-b5cc120bd179"))
            )
            mozaikLayout.onItemClick = OnItemClick { position, mozaikItems, transitionVew ->
                val photoOverlayActions = PhotoOverlayActions(binding.context)

                photoOverlayActions.setTitle(position = position + 1, count = mozaikItems.size)
                photoOverlayActions.onCloseClick = {
                    stfalconImageViewer?.close()
                }

                stfalconImageViewer = StfalconImageViewer.Builder(itemView.context, mozaikItems) { view, image ->
                    Glide.with(view.context).load(image.url).into(view)
                }.withStartPosition(position)
                        .withImageChangeListener {
                            stfalconImageViewer?.updateTransitionImage(mozaikLayout.getItemByPosition(it))
                        }
                        .withDismissListener {
                            stfalconImageViewer = null
                        }
                        .withOverlayView(photoOverlayActions)
                        .withTransitionFrom(transitionVew)
                        .withImageChangeListener {
                            photoOverlayActions.setTitle(position = it + 1, count = mozaikItems.size)
                        }
                        .show()
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
}