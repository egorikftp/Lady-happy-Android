package com.egoriku.ladyhappy.catalog.root.koin

import com.egoriku.ladyhappy.catalog.root.data.repository.TabRepository
import com.egoriku.ladyhappy.catalog.root.domain.usecase.TabUseCase
import com.egoriku.ladyhappy.catalog.root.presentation.RootCatalogViewModel
import com.egoriku.ladyhappy.catalog.root.presentation.fragment.RootCatalogFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule = module {
    scope<RootCatalogFragment> {
        scoped { TabUseCase(tabRepository = get()) }
        scoped { TabRepository(firebase = get()) }

        viewModel {
            RootCatalogViewModel(tabUseCase = get())
        }
    }
}