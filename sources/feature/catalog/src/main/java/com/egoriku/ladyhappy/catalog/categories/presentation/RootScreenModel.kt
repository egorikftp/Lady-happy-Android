package com.egoriku.ladyhappy.catalog.categories.presentation

import com.egoriku.ladyhappy.catalog.categories.domain.model.TabItem

sealed class RootScreenModel {
    class Progress : RootScreenModel()
    class Error : RootScreenModel()
    class Success(val data: List<TabItem>) : RootScreenModel()
}