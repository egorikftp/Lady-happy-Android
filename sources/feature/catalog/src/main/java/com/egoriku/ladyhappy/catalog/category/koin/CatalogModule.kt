package com.egoriku.ladyhappy.catalog.category.koin

import com.egoriku.ladyhappy.catalog.category.data.datasource.CategoriesDataSource
import com.egoriku.ladyhappy.catalog.category.data.datasource.LatestHatsDataSource
import com.egoriku.ladyhappy.catalog.category.data.repository.CategoriesRepository
import com.egoriku.ladyhappy.catalog.category.data.repository.LatestHatsRepository
import com.egoriku.ladyhappy.catalog.category.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.catalog.category.presentation.CatalogListViewModel
import com.egoriku.ladyhappy.catalog.category.presentation.fragment.CatalogFragment
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