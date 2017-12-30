package com.egoriku.ladyhappy.presentation.fragment.allgoods

import com.egoriku.ladyhappy.domain.models.CategoryModel
import com.egoriku.ladyhappy.presentation.adapter.model.NewsItem

data class AllGoodsScreenModel(
        var categories: List<CategoryModel> = emptyList(),
        var news: List<NewsItem> = emptyList()
) {
    fun hasCategories() = !categories.isEmpty()

    fun hasNews() = !news.isEmpty()

    fun isEmpty() = hasCategories() && hasNews()
}