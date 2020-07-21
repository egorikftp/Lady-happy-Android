package com.egoriku.ladyhappy.postcreator.domain.dialog

sealed class DialogResult {

    data class Category(
            val category: String
    ) : DialogResult()

    data class SubCategory(
            val subCategory: String
    ) : DialogResult()
}