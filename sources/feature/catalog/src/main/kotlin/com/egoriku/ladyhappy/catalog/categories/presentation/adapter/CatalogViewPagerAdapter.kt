package com.egoriku.ladyhappy.catalog.categories.presentation.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment.ARGUMENT_CATEGORY_ID
import com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment.SubCategoryFragment

class CatalogViewPagerAdapter(private val fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val categories = mutableListOf<TabItem>()

    override fun getItemCount(): Int = categories.size

    override fun getItemId(position: Int) = categories[position].categoryId.toLong()

    override fun containsItem(itemId: Long) = categories.any {
        it.categoryId.toLong() == itemId
    }

    override fun createFragment(position: Int): Fragment = SubCategoryFragment().apply {
        arguments = bundleOf(ARGUMENT_CATEGORY_ID to categories[position].categoryId)
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val tag = "f" + holder.itemId
            val fragment = fragment.childFragmentManager.findFragmentByTag(tag)

            when {
                fragment != null -> (fragment as SubCategoryFragment).forceUpdate()
                else -> super.onBindViewHolder(holder, position, payloads)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    fun setItems(newItems: List<TabItem>) {
        val callback = TabsPagerDiffUtil(categories, newItems)
        val diff = DiffUtil.calculateDiff(callback)

        categories.clear()
        categories.addAll(newItems)

        diff.dispatchUpdatesTo(this)
    }
}