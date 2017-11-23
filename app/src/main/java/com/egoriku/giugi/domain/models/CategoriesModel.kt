package com.egoriku.giugi.domain.models

data class CategoriesModel(
        var categories: Map<String, CategoryModel> = hashMapOf()
)