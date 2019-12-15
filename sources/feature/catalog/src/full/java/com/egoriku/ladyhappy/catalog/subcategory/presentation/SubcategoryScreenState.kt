package com.egoriku.ladyhappy.catalog.subcategory.presentation

import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem

sealed class SubcategoryScreenState {

    class Success(val screenData: List<SubCategoryItem>) : SubcategoryScreenState()

    class Error : SubcategoryScreenState()
}