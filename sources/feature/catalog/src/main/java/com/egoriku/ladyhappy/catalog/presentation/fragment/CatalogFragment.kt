package com.egoriku.ladyhappy.catalog.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.egoriku.ladyhappy.catalog.R
import com.egoriku.ladyhappy.catalog.presentation.adapter.controller.CatalogController
import com.egoriku.ladyhappy.catalog.presentation.adapter.decorator.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_catalog.*
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val catalogAdapter = EasyAdapter().apply {
        setFirstInvisibleItemEnabled(false)
    }

    private lateinit var catalogController: CatalogController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        catalogController = CatalogController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        catalogRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catalogAdapter
            addItemDecoration(MarginItemDecoration(context.resources.getDimension(R.dimen.adapter_item_catalog_margin).toInt()))
        }

        catalogAdapter.setItems(
                ItemList.create()
                        .addAll(listOf("1", "2", "3", "4", "5", "6"), catalogController)
        )
    }
}