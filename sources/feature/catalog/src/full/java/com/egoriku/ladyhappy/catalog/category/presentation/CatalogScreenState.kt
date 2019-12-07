package com.egoriku.ladyhappy.catalog.category.presentation

import com.egoriku.ladyhappy.catalog.category.domain.model.CatalogItem

sealed class CatalogScreenState {

    class Success(val screenData: List<CatalogItem>) : CatalogScreenState()

    class Error() : CatalogScreenState()
}