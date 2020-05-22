package com.egoriku.ladyhappy.catalog.subcategory.presentation.controller

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.databinding.AdapterItemSubcategoryBinding
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.extensions.colorFromAttr
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.inflater
import com.egoriku.ladyhappy.extensions.visible
import com.egoriku.mozaik.binder.NonClickablePhotosBinder
import com.egoriku.mozaik.model.MozaikImageItem
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import kotlin.properties.Delegates

internal class SubCategoryController(
        private val onCatalogItemClick: (item: SubCategoryItem) -> Unit,
        private val onTrendingClick: (view: View) -> Unit
) : BindableItemController<SubCategoryItem, SubCategoryController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) =
            Holder(AdapterItemSubcategoryBinding.inflate(parent.inflater(), parent, false))

    override fun getItemId(data: SubCategoryItem) = data.hashCode().toString()

    inner class Holder(
            private val binding: AdapterItemSubcategoryBinding
    ) : BindableViewHolder<SubCategoryItem>(binding.root) {

        private var subCategoryItem: SubCategoryItem by Delegates.notNull()

        private val photosBinder = NonClickablePhotosBinder { view: ImageView, imageItem: MozaikImageItem ->
            Glide.with(itemView.context)
                    .load(imageItem.originalUrl)
                    .placeholder(ColorDrawable(itemView.colorFromAttr(R.attr.colorPlaceholder)))
                    .transition(withCrossFade())
                    .into(view)
        }

        init {
            itemView.setOnClickListener {
                onCatalogItemClick(subCategoryItem)
            }

            binding.trending.setOnClickListener {
                onTrendingClick(it)
            }
        }

        override fun bind(data: SubCategoryItem) {
            subCategoryItem = data

            binding.bind(data)
        }

        private fun AdapterItemSubcategoryBinding.bind(data: SubCategoryItem) {
            if (data.images.isNotEmpty()) {
                cardView.visible()
                photosBinder.displayPhotos(
                        photos = data.images,
                        container = mozaikLayout
                )

                when {
                    data.isPopular -> trending.visible()
                    else -> trending.gone()
                }
            } else {
                cardView.gone()
            }

            subCategoryTitle.text = data.name
            subCategorySize.text = String.format(root.context.getString(R.string.catalog_images_count), data.publishedCount)
        }
    }
}