package com.egoriku.ladyhappy.catalog.categories.data.entity

import com.google.firebase.firestore.PropertyName

data class TabEntity(
        @PropertyName("id")
        val id: Int? = null,

        @PropertyName("name")
        val name: String? = null
)