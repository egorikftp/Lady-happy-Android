package com.egoriku.giugi.data.entities

import com.google.firebase.database.PropertyName

data class CategoryEntity(
        @get:PropertyName("id") @set:PropertyName("id") var id: Int = 0,
        @get:PropertyName("title") @set:PropertyName("title") var title: String = "",
        @get:PropertyName("imageUrl") @set:PropertyName("imageUrl") var imageUrl: String = ""
)