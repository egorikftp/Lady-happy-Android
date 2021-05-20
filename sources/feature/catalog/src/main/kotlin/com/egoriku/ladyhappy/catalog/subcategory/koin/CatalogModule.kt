package com.egoriku.ladyhappy.catalog.subcategory.koin

import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.ISubcategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.datasource.SubcategoryDataSource
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.ISubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.data.repository.SubcategoryRepository
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.CatalogUseCase
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.ICatalogUseCase
import com.egoriku.ladyhappy.catalog.subcategory.presentation.SubCategoriesViewModel
import com.egoriku.ladyhappy.catalog.subcategory.presentation.fragment.SubCategoryFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val subcategoryModule = module {
    scope<SubCategoryFragment> {
        scoped<ISubcategoryDataSource> { SubcategoryDataSource(firebase = get()) }

        scoped<ISubcategoryRepository> { SubcategoryRepository(subcategoryDataSource = get()) }

        scoped<ICatalogUseCase> {
            CatalogUseCase(
                subcategoryRepository = get(),
                stringResource = get()
            )
        }

        viewModel { (categoryId: Int) ->
            SubCategoriesViewModel(
                categoryId = categoryId,
                catalogUseCase = get(),
                featureProvider = get(),
                router = get()
            )
        }
    }
}