package com.egoriku.ladyhappy.catalog.presentation

import com.egoriku.ladyhappy.catalog.domain.model.CatalogItem

sealed class CatalogScreenState {

    class Success(val screenData: List<CatalogItem>) : CatalogScreenState()

    class Error() : CatalogScreenState()
}