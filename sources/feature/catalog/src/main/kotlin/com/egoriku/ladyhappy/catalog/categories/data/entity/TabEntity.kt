package com.egoriku.ladyhappy.catalog.categories.data.entity

import com.google.firebase.firestore.PropertyName

class TabEntity(
        @PropertyName("id")
        @get:PropertyName("id")
        val id: Int? = null,

        @PropertyName("name")
        @get:PropertyName("name")
        val name: String? = null
)