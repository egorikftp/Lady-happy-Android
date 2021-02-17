package com.egoriku.ladyhappy.catalog.subcategory.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.ICatalogUseCase
import com.egoriku.ladyhappy.catalog.subcategory.presentation.screen.DetailPageScreen
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.network.ResultOf

class SubCategoriesViewModel(
        private val categoryId: Int,
        private val featureProvider: IFeatureProvider,
        private val router: IRouter,
        private val catalogUseCase: ICatalogUseCase,
) : ViewModel() {

    val subcategoryItems: LiveData<SubcategoryScreenState> = liveData {
        emit(SubcategoryScreenState.Loading)

        when (val result = catalogUseCase.loadSubCategories(categoryId)) {
            is ResultOf.Success -> emit(SubcategoryScreenState.Success(result.value))
            is ResultOf.Failure -> emit(SubcategoryScreenState.Error)
        }
    }

    fun openDetailPage(subCategoryItem: SubCategoryItem) {
        router.addScreenFullscreen(
                screen = DetailPageScreen(
                        featureProvider = featureProvider,
                        subCategoryItem = subCategoryItem
                ))
    }
}