package com.egoriku.ladyhappy.data.entities

import android.support.annotation.Keep
import com.egoriku.corelib_kt.Constants
import com.google.firebase.firestore.PropertyName

@Keep
data class CategoryEntity(
        @get:PropertyName("id")
        @set:PropertyName("id")
        var id: Int = 0,

        @get:PropertyName("key")
        @set:PropertyName("key")
        var key: String = Constants.EMPTY,

        @get:PropertyName("title")
        @set:PropertyName("title")
        var title: String = Constants.EMPTY,

        @get:PropertyName("imageUrl")
        @set:PropertyName("imageUrl")
        var imageUrl: String = Constants.EMPTY
)