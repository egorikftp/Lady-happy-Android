package com.egoriku.ladyhappy.catalog.root.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.root.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.root.presentation.RootCatalogViewModel
import com.egoriku.ladyhappy.catalog.root.presentation.RootScreenModel
import com.egoriku.ladyhappy.catalog.root.presentation.adapter.CatalogViewPagerAdapter
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.visible
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.full.fragment_root_catalog.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class RootCatalogFragment : Fragment(R.layout.fragment_root_catalog) {

    private lateinit var catalogViewPagerAdapter: FragmentStateAdapter

    private val viewModelScope = getKoin().getOrCreateScope("RootCatalogFragment", named<RootCatalogFragment>())
    private val rootCatalogViewModel: RootCatalogViewModel = viewModelScope.get()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootCatalogViewModel.screenModel.observe(viewLifecycleOwner) {
            when (it) {
                is RootScreenModel.Progress -> {
                    tabLayout.gone()
                    progressView.visible()
                }

                is RootScreenModel.Success -> {
                    progressView.gone()
                    tabLayout.visible()

                    setupTabLayout(it.data)
                }

                is RootScreenModel.Error -> {
                    progressView.gone()
                    Toast.makeText(context, "Error", LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupTabLayout(tabData: List<TabItem>) {
        catalogViewPagerAdapter = CatalogViewPagerAdapter(this, tabData)

        viewPager.adapter = catalogViewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabData[position].name
        }.attach()
    }
}