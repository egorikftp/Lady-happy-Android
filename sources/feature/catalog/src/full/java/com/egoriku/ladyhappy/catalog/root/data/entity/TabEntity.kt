package com.egoriku.ladyhappy.catalog.root.data.entity

import com.google.firebase.firestore.PropertyName

class TabEntity {

    @PropertyName("id")
    @JvmField
    val id: Int? = null

    @PropertyName("name")
    @JvmField
    val name: String? = null

    @PropertyName("documentId")
    @JvmField
    val documentId: String? = null
}