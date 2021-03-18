package com.egoriku.ladyhappy.postcreator.domain.predefined

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CategoryModel(
        val categoryId: Int,
        val name: String
)

data class SubCategoryModel(
        val categoryId: Int,
        val subCategoryId: Int,
        val name: String
)

@Parcelize
data class ColorModel(
        val colorId: Int,
        val name: String,
        val colorHex: String
) : Parcelable