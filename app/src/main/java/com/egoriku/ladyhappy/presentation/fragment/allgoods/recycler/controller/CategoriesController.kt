package com.egoriku.ladyhappy.presentation.fragment.allgoods.recycler.controller

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.domain.models.CategoryModel
import kotlinx.android.synthetic.main.adapter_item_category.view.*
import ru.surfstudio.easyadapter.recycler.controller.BindableItemController
import ru.surfstudio.easyadapter.recycler.holder.BindableViewHolder

class CategoriesController(val onClickListener: (categoriesModel: CategoryModel) -> Unit)
    : BindableItemController<CategoryModel, CategoriesController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: CategoryModel) = data.id.hashCode().toLong()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<CategoryModel>(parent, R.layout.adapter_item_category) {

        private lateinit var category: CategoryModel
        private val title: TextView
        private val imageCategory: ImageView

        init {
            itemView.setOnClickListener { onClickListener.invoke(category) }
            title = itemView.title
            imageCategory = itemView.imageCategoryView
        }

        override fun bind(data: CategoryModel) {
            category = data
            title.text = data.title

            Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .thumbnail(0.5f)
                    .into(imageCategory)
        }
    }
}