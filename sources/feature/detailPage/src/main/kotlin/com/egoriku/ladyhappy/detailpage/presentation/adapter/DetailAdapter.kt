package com.egoriku.ladyhappy.detailpage.presentation.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.core.sharedmodel.key.CROSSFADE_DURATION
import com.egoriku.ladyhappy.detailpage.databinding.AdapterItemDetailBinding
import com.egoriku.ladyhappy.detailpage.domain.model.DetailModel
import com.egoriku.ladyhappy.extensions.context
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.mozaik.OnItemClick
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.ui.view.PhotoOverlayActions
import com.stfalcon.imageviewer.StfalconImageViewer

class DetailAdapter : PagingDataAdapter<DetailModel, DetailAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ) = VH(AdapterItemDetailBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    inner class VH(
        private val binding: AdapterItemDetailBinding,
    ) : BaseViewHolder<DetailModel>(binding.root) {

        private var stfalconImageViewer: StfalconImageViewer<MozaikItem>? = null

        init {
            binding.mozaikLayout.onViewReady = { view, url ->
                Glide.with(itemView.context)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_DURATION))
                    .into(view)
            }
        }

        override fun bind(item: DetailModel) = binding.bind(item)

        private fun AdapterItemDetailBinding.bind(item: DetailModel) {
            descriptionTextView.isVisible = item.description.isNotEmpty()
            descriptionTextView.text = item.description

            dateTextView.text = item.date

            mozaikLayout.setItems(item.images)
            mozaikLayout.onItemClick = OnItemClick { position, images, transitionVew ->
                val photoOverlayActions = PhotoOverlayActions(binding.context)

                photoOverlayActions.setTitle(position = position + 1, count = images.size)
                photoOverlayActions.onCloseClick = {
                    stfalconImageViewer?.close()
                }

                stfalconImageViewer =
                    StfalconImageViewer.Builder(itemView.context, images) { view, image ->
                        Glide.with(view.context).load(image.url).into(view)
                    }.withStartPosition(position)
                        .withDismissListener {
                            stfalconImageViewer = null
                        }
                        .withOverlayView(photoOverlayActions)
                        .withTransitionFrom(transitionVew)
                        .withImageChangeListener {
                            photoOverlayActions.setTitle(position = it + 1, count = images.size)
                            stfalconImageViewer?.updateTransitionImage(
                                mozaikLayout.getItemByPosition(
                                    it
                                )
                            )
                        }
                        .show()
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<DetailModel>() {

        override fun areItemsTheSame(oldItem: DetailModel, newItem: DetailModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DetailModel, newItem: DetailModel) =
            oldItem == newItem
    }
}