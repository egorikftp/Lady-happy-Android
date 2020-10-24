package com.egoriku.ladyhappy.settings.data.entity

import com.google.firebase.firestore.PropertyName

data class PublishPostsFeatureEntity(
        @PropertyName("isAvailable")
        val isAvailable: Boolean = false
)