package com.egoriku.ladyhappy.photoreport.presentation.adapter

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.context
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.mozaik.OnItemClick
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.photoreport.R
import com.egoriku.ladyhappy.photoreport.databinding.AdapterItemPhotoReportBinding
import com.egoriku.ladyhappy.photoreport.domain.model.PhotoReportModel
import com.egoriku.ladyhappy.ui.view.PhotoOverlayActions
import com.stfalcon.imageviewer.StfalconImageViewer

class PhotoReportAdapter : BaseListAdapter<PhotoReportModel, PhotoReportAdapter.Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
            Holder(AdapterItemPhotoReportBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
            holder: Holder,
            position: Int,
            model: PhotoReportModel
    ) = holder.bind(model)

    inner class Holder(
            private val binding: AdapterItemPhotoReportBinding
    ) : BaseViewHolder<PhotoReportModel>(binding.root) {

        private var stfalconImageViewer: StfalconImageViewer<MozaikItem>? = null

        private val colorDrawable = ColorDrawable(itemView.colorFromAttr(R.attr.colorPlaceholder))
        private val options = RequestOptions().centerCrop()

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

        override fun bind(item: PhotoReportModel) = binding.bind(item)

        private fun AdapterItemPhotoReportBinding.bind(data: PhotoReportModel) {
            newsDateTextView.text = data.date
            descriptionTextView.text = data.description

            mozaikLayout.setItems(data.images)
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
                        .withImageChangeListener {
                            photoOverlayActions.setTitle(position = it + 1, count = mozaikItems.size)
                        }
                        .withTransitionFrom(transitionVew)
                        .withHiddenStatusBar(false)
                        .show()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PhotoReportModel>() {

        override fun areItemsTheSame(oldItem: PhotoReportModel, newItem: PhotoReportModel) = oldItem == newItem

        override fun areContentsTheSame(oldItem: PhotoReportModel, newItem: PhotoReportModel) = oldItem == newItem
    }
}