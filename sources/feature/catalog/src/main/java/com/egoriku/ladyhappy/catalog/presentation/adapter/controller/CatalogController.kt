package com.egoriku.ladyhappy.catalog.presentation.adapter.controller

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.domain.model.CatalogItem
import com.egoriku.ladyhappy.extensions.colorCompat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_item_catalog.*
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class CatalogController(
        val onCatalogItemClick: (item: CatalogItem) -> Unit
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
                    .load(data.itemLogoUrl)
                    .into(catalogImage)

            lastItemsViewIds.forEachIndexed { index, imageView ->
                Glide.with(itemView.context)
                        .load(data.lastHatsImageUrl[index])
                        .placeholder(ColorDrawable(itemView.colorCompat(R.color.RealBlack30)))
                        .transition(withCrossFade())
                        .into(imageView)
            }
        }
    }
}