package com.egoriku.ladyhappy.catalog.koin

import com.egoriku.ladyhappy.catalog.presentation.CatalogListViewModel
import com.egoriku.ladyhappy.catalog.presentation.fragment.CatalogFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CatalogModule {

    val module = module {

        scope(named<CatalogFragment>()) {
            viewModel { CatalogListViewModel() }
        }
    }
}