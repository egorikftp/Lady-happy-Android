package com.egoriku.photoreport.presentation.controller

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.photoreport.databinding.AdapterItemPhotoReportCarouselBinding
import com.egoriku.photoreport.domain.model.PhotoReportModel
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoReportCarouselController(
        private val viewPool: RecyclerView.RecycledViewPool
) : BindableItemController<PhotoReportModel, PhotoReportCarouselController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemPhotoReportCarouselBinding.inflate(parent.inflater(), parent, false))

    override fun getItemId(data: PhotoReportModel) = data.hashCode().toString()

    inner class Holder(
            private val itemBinding: AdapterItemPhotoReportCarouselBinding
    ) : BindableViewHolder<PhotoReportModel>(itemBinding.root) {

        private val photoReportItemController: PhotoReportItemController = PhotoReportItemController()
        private lateinit var newsModel: PhotoReportModel

        init {
            itemBinding.reportCarouselRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setRecycledViewPool(viewPool)
            }

            PagerSnapHelper().attachToRecyclerView(itemBinding.reportCarouselRecyclerView)
            itemBinding.pagerIndicator.attachToRecyclerView(itemBinding.reportCarouselRecyclerView)
        }

        override fun bind(data: PhotoReportModel) {
            newsModel = data

            itemBinding.newsDateTextView.text = data.date
            itemBinding.descriptionTextView.text = data.description

            if (itemBinding.reportCarouselRecyclerView.adapter == null) {
                itemBinding.reportCarouselRecyclerView.adapter = EasyAdapter().apply {
                    isFirstInvisibleItemEnabled = false
                    setItems(ItemList.create().addAll(data.images, photoReportItemController))
                }
            } else {
                (itemBinding.reportCarouselRecyclerView.adapter as EasyAdapter)
                        .setItems(ItemList.create().addAll(data.images, photoReportItemController))
            }
        }
    }
}