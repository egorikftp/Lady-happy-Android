package com.egoriku.ladyhappy.catalog.root.presentation.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egoriku.ladyhappy.catalog.root.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment.ARGUMENT_CATEGORY_ID
import com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment.SubCategoryFragment

class CatalogViewPagerAdapter(
        fragment: Fragment,
        private val categories: List<TabItem>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment = SubCategoryFragment().apply {
        arguments = bundleOf(ARGUMENT_CATEGORY_ID to categories[position].categoryId)
    }
}