package com.egoriku.ladyhappy.postcreator.domain.dialog

import com.egoriku.ladyhappy.postcreator.domain.predefined.CategoryModel

sealed class DialogResult {

    data class Categories(
            val category: CategoryModel
    ) : DialogResult()
}