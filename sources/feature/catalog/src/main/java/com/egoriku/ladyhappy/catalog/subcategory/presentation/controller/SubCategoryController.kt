package com.egoriku.ladyhappy.catalog.subcategory.presentation.controller

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.egoriku.extensions.colorFromAttr
import com.egoriku.extensions.gone
import com.egoriku.extensions.inflater
import com.egoriku.extensions.visible
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.databinding.AdapterItemSubcategoryBinding
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import kotlin.properties.Delegates

private const val CROSSFADE_DURATION = 100

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

        private val colorDrawable = ColorDrawable(itemView.colorFromAttr(R.attr.colorPlaceholder))
        private val options = RequestOptions().centerCrop()

        private var subCategoryItem: SubCategoryItem by Delegates.notNull()

        init {
            binding.mozaikLayout.onViewReady = { view, url ->
                Glide.with(itemView.context)
                        .load(url)
                        .placeholder(colorDrawable)
                        .transition(withCrossFade(CROSSFADE_DURATION))
                        .apply(options)
                        .into(view)
            }

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
                mozaikLayout.setItems(data.images)

                when {
                    data.isPopular -> trending.visible()
                    else -> trending.gone()
                }
            } else {
                cardView.gone()
            }

            subCategoryTitle.text = data.name
            subCategorySize.text = String.format(
                    root.context.getString(R.string.catalog_images_count),
                    data.publishedCount
            )
        }
    }
}