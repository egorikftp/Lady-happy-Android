package com.egoriku.ladyhappy.catalog.subcategory.koin

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.SubcategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.SubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubCategoriesViewModel
import com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment.SubCategoryFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val subcategoryModule = module {
    scope<SubCategoryFragment> {
        scoped {
            CatalogUseCase(subcategoryRepository = get())
        }
        scoped { SubcategoryRepository(subcategoryDataSource = get()) }
        scoped { SubcategoryDataSource(firebase = get()) }

        viewModel { (categoryId: Int) ->
            SubCategoriesViewModel(
                    catalogUseCase = get(),
                    categoryId = categoryId
            )
        }
    }
}