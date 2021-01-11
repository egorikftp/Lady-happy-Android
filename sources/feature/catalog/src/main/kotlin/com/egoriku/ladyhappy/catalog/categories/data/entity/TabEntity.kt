package com.egoriku.ladyhappy.catalog.categories.data.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class TabEntity(
        @PropertyName("id")
        @JvmField
        val id: Int = -1,

        @PropertyName("name")
        @JvmField
        val name: String = EMPTY
)