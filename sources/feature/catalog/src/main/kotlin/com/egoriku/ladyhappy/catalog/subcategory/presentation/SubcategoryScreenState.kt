package com.egoriku.ladyhappy.catalog.subcategory.presentation

import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel

sealed class SubcategoryScreenState {

    class Success(val screenData: List<SubCategoryModel>) : SubcategoryScreenState()

    object Error : SubcategoryScreenState()

    object Loading : SubcategoryScreenState()
}