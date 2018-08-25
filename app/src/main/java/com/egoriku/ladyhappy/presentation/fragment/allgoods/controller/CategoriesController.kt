package com.egoriku.ladyhappy.presentation.fragment.allgoods.controller

import android.support.v4.view.ViewCompat
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.domain.models.SingleCategoryModel
import kotlinx.android.synthetic.main.adapter_item_category.view.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class CategoriesController(val onClickListener: (categoriesModel: SingleCategoryModel, imageView: ImageView) -> Unit)
    : BindableItemController<SingleCategoryModel, CategoriesController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: SingleCategoryModel) = data.id.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<SingleCategoryModel>(parent, R.layout.adapter_item_category) {

        private lateinit var category: SingleCategoryModel
        private val title: TextView
        private val imageCategory: ImageView

        init {
            itemView.setOnClickListener { onClickListener(category, itemView.imageCategoryView) }
            title = itemView.title
            imageCategory = itemView.imageCategoryView
        }

        override fun bind(data: SingleCategoryModel) {
            category = data
            title.text = data.title

            Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .thumbnail(0.5f)
                    .into(imageCategory)

            ViewCompat.setTransitionName(itemView.imageCategoryView, data.title)
        }
    }
}