package com.egoriku.ladyhappy.auth.permission.entity

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.google.firebase.firestore.PropertyName

class UserEntity(
        @PropertyName("permissions")
        @JvmField
        val permissions: List<String> = emptyList(),

        @PropertyName("uid")
        @JvmField
        val userId: String = EMPTY
)