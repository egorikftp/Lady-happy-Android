package com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.egoriku.ladyhappy.auth.permission.IUserPermission
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.databinding.FragmentCatalogBinding
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubCategoriesViewModel
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubcategoryScreenState
import com.egoriku.ladyhappy.catalog.subcategory.presentation.controller.SubCategoriesAdapter
import com.egoriku.ladyhappy.catalog.subcategory.presentation.controller.balloon.ViewHolderBalloonFactory
import com.egoriku.ladyhappy.extensions.extraNotNull
import com.egoriku.ladyhappy.extensions.gone
import com.egoriku.ladyhappy.extensions.visible
import com.skydoves.balloon.balloon
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

private const val INITIAL_PREFETCH_COUNT = 7
const val ARGUMENT_CATEGORY_ID = "category_id"

class SubCategoryFragment : ScopeFragment(R.layout.fragment_catalog) {

    private val permissions: IUserPermission by inject()

    private val binding by viewBinding(FragmentCatalogBinding::bind)

    private val categoryIdExtra by extraNotNull<Int>(ARGUMENT_CATEGORY_ID)

    private val catalogViewModel by viewModel<SubCategoriesViewModel> {
        parametersOf(categoryIdExtra)
    }

    private val viewHolderBalloon by balloon<ViewHolderBalloonFactory>()

    private var subCategoriesAdapter: SubCategoriesAdapter by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subCategoriesAdapter = SubCategoriesAdapter(
                onCatalogItemClick = {
                    catalogViewModel.openDetailPage(it)
                },
                onTrendingClick = {
                    viewHolderBalloon.showAlignLeft(it)
                },
                onLongPressListener = {
                    if (permissions.isAbleToEditPosts) {
                        catalogViewModel.openEditPage(it)
                    }
                }
        )

        binding.catalogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                initialPrefetchItemCount = INITIAL_PREFETCH_COUNT
            }

            adapter = subCategoriesAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }

        lifecycleScope.launch {
            catalogViewModel.subcategoryItems.collect {
                binding.render(it)
            }
        }
    }

    private fun FragmentCatalogBinding.render(screenState: SubcategoryScreenState) {
        when (screenState) {
            is SubcategoryScreenState.Success -> {
                errorView.gone()
                progressBar.gone()
                subCategoriesAdapter.submitList(screenState.screenData)
            }
            is SubcategoryScreenState.Error -> {
                errorView.visible()
                progressBar.gone()
            }
            is SubcategoryScreenState.Loading -> {
                errorView.gone()
                progressBar.visible()
            }
        }
    }

    fun forceUpdate() = catalogViewModel.forceUpdate()
}