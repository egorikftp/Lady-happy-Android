package com.egoriku.ladyhappy.settings.data.entity

import com.google.firebase.firestore.PropertyName

class PublishPostsFeatureEntity(
        @PropertyName("isAvailable")
        @JvmField
        val isAvailable: Boolean = false
)