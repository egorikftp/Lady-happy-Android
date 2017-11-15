package com.egoriku.giugi.data.entities

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.google.firebase.database.Exclude

@Keep
data class AllGoodsEntity(
        @SerializedName("categories") private var tasks: HashMap<String, CategoriesEntity>
) {

    @Exclude
    fun isEmpty(): Boolean = tasks.isEmpty()

}