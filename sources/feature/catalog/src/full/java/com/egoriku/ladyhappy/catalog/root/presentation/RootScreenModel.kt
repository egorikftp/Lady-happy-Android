package com.egoriku.ladyhappy.catalog.root.presentation

import com.egoriku.ladyhappy.catalog.root.domain.model.TabItem

sealed class RootScreenModel {
    class Progress() : RootScreenModel()
    class Error() : RootScreenModel()
    class Success(val data: List<TabItem>) : RootScreenModel()
}