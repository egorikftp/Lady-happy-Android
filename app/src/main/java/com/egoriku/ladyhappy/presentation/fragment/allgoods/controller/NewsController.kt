package com.egoriku.ladyhappy.presentation.fragment.allgoods.controller

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.common.scrollPercentage
import com.egoriku.ladyhappy.common.view.DotsView
import com.egoriku.ladyhappy.domain.models.SingleNewsModel
import kotlinx.android.synthetic.main.adapter_item_news.view.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class NewsController : BindableItemController<SingleNewsModel, NewsController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)
    override fun getItemId(data: SingleNewsModel) = data.hashCode().toString()

    @Suppress("JoinDeclarationAndAssignment")
    inner class Holder(parent: ViewGroup) : BindableViewHolder<SingleNewsModel>(parent, R.layout.adapter_item_news) {

        private lateinit var newsModel: SingleNewsModel

        private val newsImagesController: NewsImagesController

        private val description: TextView
        private val newsRecyclerView: RecyclerView
        private val dots: DotsView
        private val newsDate: TextView

        init {
            newsImagesController = NewsImagesController()

            dots = itemView.dotsView
            newsDate = itemView.newsDate
            description = itemView.descriptionTextView
            newsRecyclerView = itemView.newsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
                    initialPrefetchItemCount = 1
                }

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        dots.updateScrollPosition(recyclerView.scrollPercentage())
                    }
                })
            }

            PagerSnapHelper().attachToRecyclerView(newsRecyclerView)
        }

        override fun bind(data: SingleNewsModel) {
            newsModel = data
            newsDate.text = data.date
            description.text = data.description
            dots.setDotCount(data.images.size)

            if (newsRecyclerView.adapter == null) {
                newsRecyclerView.adapter = EasyAdapter().apply {
                    setItems(ItemList.create().addAll(data.images, newsImagesController))
                }
            } else {
                (newsRecyclerView.adapter as EasyAdapter)
                        .setItems(ItemList.create().addAll(data.images, newsImagesController))
            }
        }
    }
}