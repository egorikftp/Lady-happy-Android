package com.egoriku.ladyhappy.postcreator.domain.predefined

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class HatsStructure(
    @SerializedName("colors")
    val hatColors: List<HatColor>,

    @SerializedName("categories")
    val hatCategories: List<HatCategory>
)

data class HatCategory(
    @SerializedName("categoryId")
    val categoryId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("subcategories")
    val subcategories: List<HatSubCategory>
)

data class HatSubCategory(
    @SerializedName("categoryId")
    val categoryId: Int,

    @SerializedName("subCategoryId")
    val subCategoryId: Int,

    @SerializedName("name")
    val name: String
)

@Parcelize
data class HatColor(
    @SerializedName("colorId")
    val colorId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("colorHex")
    val colorHex: String
) : Parcelable