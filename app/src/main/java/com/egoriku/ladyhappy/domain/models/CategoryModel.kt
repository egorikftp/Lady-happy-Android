package com.egoriku.ladyhappy.domain.models

import com.egoriku.ladyhappy.presentation.ui.adapter.model.BaseDisplayableItem

data class CategoryModel(
        var id: Int = 0,
        var title: String = "",
        var imageUrl: String = ""
): BaseDisplayableItem