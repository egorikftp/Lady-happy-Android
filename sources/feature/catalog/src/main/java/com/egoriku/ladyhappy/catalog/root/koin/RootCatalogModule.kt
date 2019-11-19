package com.egoriku.ladyhappy.catalog.root.koin

import com.egoriku.ladyhappy.catalog.root.presentation.RootCatalogFragment
import com.egoriku.ladyhappy.catalog.root.presentation.RootCatalogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object RootCatalogModule {
    val module = module {
        scope(named<RootCatalogFragment>()) {
            viewModel {
                RootCatalogViewModel()
            }
        }
    }
}