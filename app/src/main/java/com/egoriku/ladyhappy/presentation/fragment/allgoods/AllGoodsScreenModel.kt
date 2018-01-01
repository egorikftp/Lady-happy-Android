package com.egoriku.ladyhappy.presentation.fragment.allgoods

import com.egoriku.ladyhappy.domain.models.SingleCategoryModel
import com.egoriku.ladyhappy.domain.models.SingleNewsModel

data class AllGoodsScreenModel(
        var categories: List<SingleCategoryModel> = emptyList(),
        var news: List<SingleNewsModel> = emptyList()
) {
    fun categoriesEmpty() = categories.isEmpty()

    fun newsEmpty() = news.isEmpty()

    fun isEmpty() = categoriesEmpty() && newsEmpty()
}