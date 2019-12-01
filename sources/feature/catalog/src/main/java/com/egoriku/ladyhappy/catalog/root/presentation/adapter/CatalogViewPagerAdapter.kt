package com.egoriku.ladyhappy.catalog.root.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egoriku.ladyhappy.catalog.category.presentation.fragment.CatalogFragment
import com.egoriku.ladyhappy.catalog.root.domain.model.TabItem

class CatalogViewPagerAdapter(
        fragment: Fragment,
        private val categories: List<TabItem>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment = CatalogFragment()
}