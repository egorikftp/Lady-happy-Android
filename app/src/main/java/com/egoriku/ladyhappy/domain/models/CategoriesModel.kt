package com.egoriku.ladyhappy.domain.models


data class CategoriesModel(var categories: Map<String, CategoryModel> = hashMapOf()
){

    fun isEmpty(): Boolean {
        return categories.isEmpty()
    }

    fun toList(): List<CategoryModel> = categories.values.toList()
}