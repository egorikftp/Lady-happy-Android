package com.egoriku.ladyhappy.catalog.categories.presentation

import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem

sealed class RootScreenModel {
    object Progress : RootScreenModel()
    object Error : RootScreenModel()
    class Success(val data: List<TabItem>) : RootScreenModel()
}