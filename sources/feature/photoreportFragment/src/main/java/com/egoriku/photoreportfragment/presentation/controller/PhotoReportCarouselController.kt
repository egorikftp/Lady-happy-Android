package com.egoriku.photoreportfragment.presentation.controller

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.egoriku.core.model.IPhotoReportModel
import com.egoriku.photoreportfragment.R
import com.egoriku.photoreportfragment.presentation.view.DotsView
import kotlinx.android.synthetic.main.adapter_item_photo_report_carousel.view.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoReportCarouselController : BindableItemController<IPhotoReportModel, PhotoReportCarouselController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: IPhotoReportModel) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<IPhotoReportModel>(parent, R.layout.adapter_item_photo_report_carousel) {

        private lateinit var newsModel: IPhotoReportModel

        private val photoReportItemController: PhotoReportItemController = PhotoReportItemController()

        private val description: TextView = itemView.descriptionTextView

        private val newsRecyclerView: RecyclerView = itemView.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
                initialPrefetchItemCount = 1
            }
        }
        private val dots: DotsView = itemView.dotsView
        private val newsDate: TextView = itemView.newsDate

        init {

            PagerSnapHelper().attachToRecyclerView(newsRecyclerView)
        }

        override fun bind(data: IPhotoReportModel) {
            newsModel = data
            newsDate.text = data.date
            description.text = data.description
            dots.setDotCount(data.images.size)

            if (newsRecyclerView.adapter == null) {
                newsRecyclerView.adapter = EasyAdapter().apply {
                    setItems(ItemList.create().addAll(data.images, photoReportItemController))
                }
            } else {
                (newsRecyclerView.adapter as EasyAdapter)
                        .setItems(ItemList.create().addAll(data.images, photoReportItemController))
            }
        }
    }
}