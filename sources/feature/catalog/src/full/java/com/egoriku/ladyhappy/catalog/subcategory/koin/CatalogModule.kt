package com.egoriku.ladyhappy.catalog.subcategory.koin

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.SubcategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.LatestHatsDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.SubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.LatestHatsRepository
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubCategoriesViewModel
import com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment.SubcategoryFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.ScopeID
import org.koin.dsl.module

object CatalogModule {

    val module = module {

        scope(named<SubcategoryFragment>()) {
            scoped {
                CatalogUseCase(
                        subcategoryRepository = get(),
                        latestHatsRepository = get()
                )
            }

            scoped { SubcategoryRepository(subcategoryDataSource = get()) }
            scoped { LatestHatsRepository(latestHatsDataSource = get()) }

            scoped { SubcategoryDataSource(firebase = get()) }
            scoped { LatestHatsDataSource(firebase = get()) }
        }

        viewModel { (scopeId: ScopeID, subcategory: String) ->
            SubCategoriesViewModel(
                    catalogUseCase = getScope(scopeId).get(),
                    documentId = subcategory
            )
        }
    }
}