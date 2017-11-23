package com.egoriku.giugi.data.entities

import com.google.firebase.database.PropertyName

data class CategoriesEntity(
        @get:PropertyName("categories")
        @set:PropertyName("categories")
        var categories: HashMap<String, CategoryEntity> = hashMapOf()
)