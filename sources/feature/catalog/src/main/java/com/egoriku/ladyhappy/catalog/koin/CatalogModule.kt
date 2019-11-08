package com.egoriku.ladyhappy.catalog.koin

import com.egoriku.ladyhappy.catalog.data.datasource.CategoriesDataSource
import com.egoriku.ladyhappy.catalog.data.datasource.LatestHatsDataSource
import com.egoriku.ladyhappy.catalog.data.repository.CategoriesRepository
import com.egoriku.ladyhappy.catalog.data.repository.LatestHatsRepository
import com.egoriku.ladyhappy.catalog.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.catalog.presentation.CatalogListViewModel
import com.egoriku.ladyhappy.catalog.presentation.fragment.CatalogFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CatalogModule {

    val module = module {

        scope(named<CatalogFragment>()) {
            viewModel {
                CatalogListViewModel(
                        catalogUseCase = get()
                )
            }

            scoped {
                CatalogUseCase(
                        categoriesRepository = get(),
                        latestHatsRepository = get()
                )
            }

            scoped { CategoriesRepository(categoriesDataSource = get()) }
            scoped { LatestHatsRepository(latestHatsDataSource = get()) }

            scoped { CategoriesDataSource(firebase = get()) }
            scoped { LatestHatsDataSource(firebase = get()) }
        }
    }
}