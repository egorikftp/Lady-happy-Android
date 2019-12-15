package com.egoriku.ladyhappy.catalog.subcategory.presentation.adapter.controller

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.catalog.subcategory.presentation.glide.BlurTransformation
import com.egoriku.ladyhappy.extensions.colorCompat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.full.adapter_item_catalog.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class CatalogController(
        private val onCatalogItemClick: (item: SubCategoryItem) -> Unit
) : BindableItemController<SubCategoryItem, CatalogController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: SubCategoryItem) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<SubCategoryItem>(parent, R.layout.adapter_item_catalog), LayoutContainer {

        override val containerView: View = itemView

        private val lastItemsViewIds = listOf(
                lastItemImage1,
                lastItemImage2,
                lastItemImage3,
                lastItemImage4,
                lastItemImage5,
                lastItemImage6
        )

        private lateinit var subCategoryItem: SubCategoryItem

        init {
            itemView.setOnClickListener {
                onCatalogItemClick(subCategoryItem)
            }
        }

        override fun bind(data: SubCategoryItem) {
            subCategoryItem = data

            catalogTitle.text = data.itemName

            Glide.with(itemView.context)
                    .asBitmap()
                    .load(data.headerImage.full)
                    .transition(withCrossFade())
                    .placeholder(ColorDrawable(itemView.colorCompat(R.color.Placeholder)))
                    .into(catalogImage)

            data.lastHats.forEachIndexed { index, s ->
                Glide.with(itemView.context)
                        .asBitmap()
                        .load(s)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(ColorDrawable(itemView.colorCompat(R.color.Placeholder)))
                        .transition(withCrossFade())
                        .thumbnail(
                                Glide.with(itemView.context)
                                        .asBitmap()
                                        .transition(withCrossFade())
                                        .load(s)
                                        .apply(RequestOptions().transform(BlurTransformation(radius = 25f)))
                        )
                        .into(lastItemsViewIds[index])
            }
        }
    }
}