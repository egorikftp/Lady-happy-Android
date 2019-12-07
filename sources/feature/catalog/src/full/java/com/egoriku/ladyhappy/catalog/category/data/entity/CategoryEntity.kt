package com.egoriku.ladyhappy.catalog.category.data.entity

import com.google.firebase.firestore.PropertyName

class CategoryEntity(
        @PropertyName("hatsType")
        val hatsType: Int,

        @PropertyName("name")
        val categoryName: String,

        @PropertyName("imageUrl")
        val imageUrl: String
)