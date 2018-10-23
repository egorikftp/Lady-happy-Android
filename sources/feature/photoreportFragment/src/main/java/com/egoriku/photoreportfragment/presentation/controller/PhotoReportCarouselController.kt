package com.egoriku.photoreportfragment.presentation.controller

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.egoriku.core.model.IPhotoReportModel
import com.egoriku.photoreportfragment.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_photo_report_carousel.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class PhotoReportCarouselController : BindableItemController<IPhotoReportModel, PhotoReportCarouselController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: IPhotoReportModel) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<IPhotoReportModel>(parent, R.layout.adapter_item_photo_report_carousel), LayoutContainer {

        override val containerView: View?
            get() = itemView

        private val photoReportItemController: PhotoReportItemController = PhotoReportItemController()
        private lateinit var newsModel: IPhotoReportModel

        init {
            reportCarouselRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }

            PagerSnapHelper().attachToRecyclerView(reportCarouselRecyclerView)
            pagerIndicator.attachToRecyclerView(reportCarouselRecyclerView)
        }

        override fun bind(data: IPhotoReportModel) {
            newsModel = data

            newsDateTextView.text = data.date
            descriptionTextView.text = data.description

            if (reportCarouselRecyclerView.adapter == null) {
                reportCarouselRecyclerView.adapter = EasyAdapter().apply {
                    setItems(ItemList.create().addAll(data.images, photoReportItemController))
                }
            } else {
                (reportCarouselRecyclerView.adapter as EasyAdapter)
                        .setItems(ItemList.create().addAll(data.images, photoReportItemController))
            }
        }
    }
}