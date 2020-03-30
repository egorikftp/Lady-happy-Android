package com.egoriku.ladyhappy.catalog.root.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egoriku.ladyhappy.catalog.databinding.FragmentRootCatalogBinding
import com.egoriku.ladyhappy.catalog.root.domain.model.TabItem
import com.egoriku.ladyhappy.catalog.root.presentation.RootCatalogViewModel
import com.egoriku.ladyhappy.catalog.root.presentation.RootScreenModel
import com.egoriku.ladyhappy.catalog.root.presentation.adapter.CatalogViewPagerAdapter
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.extensions.viewBindingLifecycle
import com.egoriku.ladyhappy.extensions.visible
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RootCatalogFragment : Fragment() {

    private var binding: FragmentRootCatalogBinding by viewBindingLifecycle()

    private lateinit var catalogViewPagerAdapter: FragmentStateAdapter

    private val rootCatalogViewModel: RootCatalogViewModel by viewModel {
        parametersOf(lifecycleScope.id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentRootCatalogBinding.inflate(inflater, container, false).apply {
            binding = this
            return root
        }
    }

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

                    setupTabLayout(it.data)
                }

                is RootScreenModel.Error -> {
                    binding.progressView.gone()
                    toast("Error")
                }
            }
        }
    }

    private fun setupTabLayout(tabData: List<TabItem>) {
        catalogViewPagerAdapter = CatalogViewPagerAdapter(this, tabData)

        binding.viewPager.adapter = catalogViewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabData[position].name
        }.attach()
    }
}