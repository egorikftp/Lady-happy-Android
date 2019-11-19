package com.egoriku.ladyhappy.catalog.category.presentation.adapter.controller

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.category.domain.model.CatalogItem
import com.egoriku.ladyhappy.catalog.category.presentation.glide.BlurTransformation
import com.egoriku.ladyhappy.extensions.colorCompat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_catalog.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class CatalogController(
        private val onCatalogItemClick: (item: CatalogItem) -> Unit
) : BindableItemController<CatalogItem, CatalogController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: CatalogItem) = data.hashCode().toString()

    inner class Holder(parent: ViewGroup) : BindableViewHolder<CatalogItem>(parent, R.layout.adapter_item_catalog), LayoutContainer {

        override val containerView: View = itemView

        private val lastItemsViewIds = listOf(
                lastItemImage1,
                lastItemImage2,
                lastItemImage3,
                lastItemImage4,
                lastItemImage5,
                lastItemImage6
        )

        private lateinit var catalogItem: CatalogItem

        init {
            itemView.setOnClickListener {
                onCatalogItemClick(catalogItem)
            }
        }

        override fun bind(data: CatalogItem) {
            catalogItem = data

            catalogTitle.text = data.itemName

            Glide.with(itemView.context)
                    .asBitmap()
                    .load(data.itemLogoUrl)
                    .transition(withCrossFade())
                    .placeholder(ColorDrawable(itemView.colorCompat(R.color.Placeholder)))
                    .into(catalogImage)

            lastItemsViewIds.forEachIndexed { index, imageView ->
                Glide.with(itemView.context)
                        .asBitmap()
                        .load(data.lastHatsImageUrl[index])
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(ColorDrawable(itemView.colorCompat(R.color.Placeholder)))
                        .transition(withCrossFade())
                        .thumbnail(
                                Glide.with(itemView.context)
                                        .asBitmap()
                                        .transition(withCrossFade())
                                        .load(data.lastHatsImageUrl[index])
                                        .apply(RequestOptions().transform(BlurTransformation(radius = 25f)))
                        )
                        .into(imageView)
            }
        }
    }
}