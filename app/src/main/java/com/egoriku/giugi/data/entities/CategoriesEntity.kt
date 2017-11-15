package com.egoriku.giugi.data.entities

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoriesEntity(
        @SerializedName("id") private val id: Int,
        @SerializedName("title") private val title: String,
        @SerializedName("imageUrl") private val imageUrl: String)