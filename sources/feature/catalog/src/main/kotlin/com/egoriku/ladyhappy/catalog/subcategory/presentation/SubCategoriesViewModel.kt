package com.egoriku.ladyhappy.catalog.subcategory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.subcategory.domain.usecase.ICatalogUseCase
import com.egoriku.ladyhappy.catalog.subcategory.presentation.screen.DetailPageScreen
import com.egoriku.ladyhappy.core.IFeatureProvider
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.network.ResultOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubCategoriesViewModel(
        private val categoryId: Int,
        private val featureProvider: IFeatureProvider,
        private val router: IRouter,
        private val catalogUseCase: ICatalogUseCase,
) : ViewModel() {

    private val _subcategoryItems = MutableStateFlow<SubcategoryScreenState>(SubcategoryScreenState.Loading)
    val subcategoryItems: StateFlow<SubcategoryScreenState> = _subcategoryItems

    init {
        load(categoryId)
    }

    fun forceUpdate() = load(categoryId = categoryId)

    fun load(categoryId: Int) {
        viewModelScope.launch {
            _subcategoryItems.value = SubcategoryScreenState.Loading

            when (val result = catalogUseCase.loadSubCategories(categoryId)) {
                is ResultOf.Success -> _subcategoryItems.value = SubcategoryScreenState.Success(result.value)
                is ResultOf.Failure -> _subcategoryItems.value = SubcategoryScreenState.Error
            }
        }
    }

    fun openDetailPage(subCategoryModel: SubCategoryModel) {
        router.addScreenFullscreen(
                screen = DetailPageScreen(
                        featureProvider = featureProvider,
                        subCategoryModel = subCategoryModel
                )
        )
    }
}