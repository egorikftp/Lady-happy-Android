package com.egoriku.ladyhappy.catalog.root.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.egoriku.ladyhappy.catalog.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_root_catalog.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class RootCatalogFragment : Fragment(R.layout.fragment_root_catalog) {

    private lateinit var catalogViewPagerAdapter: CatalogViewPagerAdapter

    private val viewModelScope = getKoin().getOrCreateScope("RootCatalogFragment", named<RootCatalogFragment>())
    private val rootCatalogViewModel: RootCatalogViewModel = viewModelScope.get()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootCatalogViewModel.categories.observe(this) {
            initializeTabLayout(it)
        }
    }

    private fun initializeTabLayout(list: List<String>) {
        catalogViewPagerAdapter = CatalogViewPagerAdapter(this, list)

        viewPager.adapter = catalogViewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = list[position]
        }.attach()
    }
}