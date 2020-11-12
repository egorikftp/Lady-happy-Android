package com.egoriku.ladyhappy.settings.data.entity

import com.google.firebase.firestore.PropertyName

class PublishPostsFeatureEntity(
        @PropertyName("isAvailable")
        @get:PropertyName("isAvailable")
        val isAvailable: Boolean = false
)