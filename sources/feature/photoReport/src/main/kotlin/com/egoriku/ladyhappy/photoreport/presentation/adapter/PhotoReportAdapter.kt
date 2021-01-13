package com.egoriku.ladyhappy.photoreport.presentation.adapter

import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.mozaik.OnItemClick
import com.egoriku.ladyhappy.mozaik.model.MozaikItem
import com.egoriku.ladyhappy.photoreport.R
import com.egoriku.ladyhappy.photoreport.databinding.AdapterItemPhotoReportBinding
import com.egoriku.ladyhappy.photoreport.domain.model.PhotoReportModel
import com.stfalcon.imageviewer.StfalconImageViewer

class PhotoReportAdapter : ListAdapter<PhotoReportModel, PhotoReportAdapter.Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(AdapterItemPhotoReportBinding.inflate(parent.inflater(), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(
            private val binding: AdapterItemPhotoReportBinding
    ) : RecyclerView.ViewHolder(binding.root) {

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

        fun bind(data: PhotoReportModel) = binding.bind(data)

        private fun AdapterItemPhotoReportBinding.bind(data: PhotoReportModel) {
            newsDateTextView.text = data.date
            descriptionTextView.text = data.description

            mozaikLayout.setItems(data.images)
            mozaikLayout.onItemClick = OnItemClick { position, mozaikItems, transitionVew ->
                stfalconImageViewer = StfalconImageViewer.Builder(itemView.context, mozaikItems) { view: ImageView, image ->
                    Glide.with(view.context)
                            .load(image.url)
                            .into(view)
                }.withStartPosition(position)
                        .withImageChangeListener {
                            stfalconImageViewer?.updateTransitionImage(mozaikLayout.getItemByPosition(it))
                        }
                        .withDismissListener {
                            stfalconImageViewer = null
                        }
                        .withTransitionFrom(transitionVew)
                        .withHiddenStatusBar(false)
                        .show()
            }
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<PhotoReportModel>() {

        override fun areItemsTheSame(oldItem: PhotoReportModel, newItem: PhotoReportModel) = oldItem == newItem

        override fun areContentsTheSame(oldItem: PhotoReportModel, newItem: PhotoReportModel) = oldItem == newItem
    }
}