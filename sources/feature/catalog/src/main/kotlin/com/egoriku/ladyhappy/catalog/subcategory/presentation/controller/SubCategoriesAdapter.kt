package com.egoriku.ladyhappy.catalog.subcategory.presentation.controller

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.databinding.AdapterItemSubcategoryBinding
import com.egoriku.ladyhappy.core.adapter.BaseListAdapter
import com.egoriku.ladyhappy.core.adapter.BaseViewHolder
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.core.sharedmodel.key.CROSSFADE_DURATION
import com.egoriku.ladyhappy.extensions.*
import kotlin.properties.Delegates

class SubCategoriesAdapter(
    private val onCatalogItemClick: (model: SubCategoryModel) -> Unit,
    private val onTrendingClick: (view: View) -> Unit,
    private val onLongPressListener: (reference: String) -> Unit
) : BaseListAdapter<SubCategoryModel, SubCategoriesAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = VH(AdapterItemSubcategoryBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(
        holder: VH,
        position: Int,
        model: SubCategoryModel
    ) = holder.bind(model)

    inner class VH(
        private val binding: AdapterItemSubcategoryBinding
    ) : BaseViewHolder<SubCategoryModel>(binding.root) {

        private var subCategoryModel: SubCategoryModel by Delegates.notNull()

        init {
            binding.mozaikLayout.onViewReady = { view, url ->
                Glide.with(itemView.context)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_DURATION))
                    .into(view)
            }

            itemView.setOnClickListener {
                onCatalogItemClick(subCategoryModel)
            }

            itemView.setOnLongClickListener {
                consume {
                    onLongPressListener.invoke(subCategoryModel.documentReference)
                }
            }

            binding.trending.setOnClickListener {
                onTrendingClick(it)
            }
        }

        override fun bind(item: SubCategoryModel) {
            subCategoryModel = item

            binding.bind(item)
        }

        private fun AdapterItemSubcategoryBinding.bind(data: SubCategoryModel) {
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

            subCategoryTitle.text = data.subCategoryName
            subCategorySize.text = context.getQuantityStringZero(
                pluralResId = R.plurals.catalog_images_count,
                zeroResId = R.string.catalog_images_count_zero,
                quantity = data.publishedCount
            )
        }
    }

    internal class DiffCallback : DiffUtil.ItemCallback<SubCategoryModel>() {

        override fun areItemsTheSame(
            oldModel: SubCategoryModel,
            newModel: SubCategoryModel
        ) = oldModel == newModel

        override fun areContentsTheSame(
            oldModel: SubCategoryModel,
            newModel: SubCategoryModel
        ) = oldModel.subCategoryId == newModel.subCategoryId
    }
}