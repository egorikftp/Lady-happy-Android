package com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.databinding.FragmentCatalogBinding
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubCategoriesViewModel
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubcategoryScreenState
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubcategoryScreenState.Success
import com.egoriku.ladyhappy.catalog.subcategory.presentation.adapter.controller.CatalogController
import com.egoriku.ladyhappy.catalog.subcategory.presentation.adapter.decorator.MarginItemDecoration
import com.egoriku.ladyhappy.extensions.toast
import com.egoriku.ladyhappy.extensions.viewBindingLifecycle
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import kotlin.properties.Delegates

const val ARGUMENT_SUBCATEGORY = "sub_category"

class SubcategoryFragment : Fragment() {

    private var binding: FragmentCatalogBinding by viewBindingLifecycle()

    private val catalogViewModel: SubCategoriesViewModel by viewModel {
        parametersOf(
                lifecycleScope.id,
                arguments?.getString(ARGUMENT_SUBCATEGORY)
        )
    }

    private var catalogController: CatalogController by Delegates.notNull()

    private val catalogAdapter = EasyAdapter().apply {
        setFirstInvisibleItemEnabled(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FragmentCatalogBinding.inflate(inflater, container, false).apply {
            binding = this
            return root
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        catalogViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catalogController = CatalogController {
            toast("Item ${it.itemName} was clicked")
        }

        binding.catalogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catalogAdapter
            addItemDecoration(MarginItemDecoration(context.resources.getDimension(R.dimen.adapter_item_catalog_margin).toInt()))
        }

        catalogViewModel.subcategoryItems.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun render(screenState: SubcategoryScreenState) {
        when (screenState) {
            is Success -> {
                catalogAdapter.setItems(
                        ItemList.create()
                                .addAll(screenState.screenData, catalogController)
                )
            }

            is Error -> {
                //TODO handle error state
            }
        }
    }
}