package com.egoriku.ladyhappy.catalog.categories.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.extensions.gone
import com.egoriku.extensions.toast
import com.egoriku.extensions.visible
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.categories.presentation.RootCatalogViewModel
import com.egoriku.ladyhappy.catalog.categories.presentation.RootScreenModel
import com.egoriku.ladyhappy.catalog.categories.presentation.adapter.CatalogViewPagerAdapter
import com.egoriku.ladyhappy.catalog.databinding.FragmentCategoriesBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

private const val OFFSET_PAGE_LIMIT = 3

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding: FragmentCategoriesBinding by viewBinding()

    private lateinit var catalogViewPagerAdapter: FragmentStateAdapter

    private val rootCatalogViewModel: RootCatalogViewModel by lifecycleScope.viewModel(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootCatalogViewModel.screenModel.observe(viewLifecycleOwner) {
            when (it) {
                is RootScreenModel.Progress -> {
                    binding.tabLayout.gone()
                    binding.progressView.visible()
                }

                is RootScreenModel.Success -> {
                    binding.progressView.gone()
                    binding.tabLayout.visible()

                    binding.setupTabLayout(it.data)
                }

                is RootScreenModel.Error -> {
                    binding.progressView.gone()
                    toast("Error")
                }
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun FragmentCategoriesBinding.setupTabLayout(tabData: List<TabItem>) {
        catalogViewPagerAdapter = CatalogViewPagerAdapter(this@CategoriesFragment, tabData)

        viewPager.adapter = catalogViewPagerAdapter
        viewPager.offscreenPageLimit = OFFSET_PAGE_LIMIT

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabData[position].categoryName
        }.attach()
    }
}