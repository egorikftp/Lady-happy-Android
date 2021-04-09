package com.egoriku.ladyhappy.catalog.categories.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.categories.presentation.RootCatalogViewModel
import com.egoriku.ladyhappy.catalog.categories.presentation.RootScreenModel
import com.egoriku.ladyhappy.catalog.categories.presentation.adapter.CatalogViewPagerAdapter
import com.egoriku.ladyhappy.catalog.databinding.FragmentCategoriesBinding
import com.egoriku.ladyhappy.core.feature.CatalogFeature
import com.egoriku.ladyhappy.core.sharedmodel.key.EDIT_BUNDLE_RESULT_KEY
import com.egoriku.ladyhappy.core.sharedmodel.key.EDIT_REQUEST_KEY
import com.egoriku.ladyhappy.extensions.*
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

private const val OFFSET_PAGE_LIMIT = 3

class CategoriesFragment : ScopeFragment(R.layout.fragment_categories), CatalogFeature {

    private val binding by viewBinding(FragmentCategoriesBinding::bind)

    private val viewModel by viewModel<RootCatalogViewModel>()

    private var catalogViewPagerAdapter: CatalogViewPagerAdapter by Delegates.notNull()

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentFragmentManager.setFragmentResultListenerWrapper(
                requestKey = EDIT_REQUEST_KEY,
                lifecycleOwner = viewLifecycleOwner
        ) { _, bundle ->
            val categoryId = bundle.getInt(EDIT_BUNDLE_RESULT_KEY)

            viewModel.updateTabs(categoryId)
        }

        catalogViewPagerAdapter = CatalogViewPagerAdapter(fragment = this)

        with(binding) {
            viewPager.adapter = catalogViewPagerAdapter
            viewPager.offscreenPageLimit = OFFSET_PAGE_LIMIT
        }

        repeatingJobOnStarted {
            viewModel.tabs.collect {
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
    }

    private fun FragmentCategoriesBinding.setupTabLayout(tabData: List<TabItem>) {
        catalogViewPagerAdapter.setItems(tabData)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabData[position].categoryName
        }.attach()
    }
}