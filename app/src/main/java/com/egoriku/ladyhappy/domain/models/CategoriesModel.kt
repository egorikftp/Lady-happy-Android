package com.egoriku.ladyhappy.domain.models

data class CategoriesModel(
        var categories: Map<String, CategoryModel> = hashMapOf()
)