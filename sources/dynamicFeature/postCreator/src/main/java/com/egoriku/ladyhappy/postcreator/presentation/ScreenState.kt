package com.egoriku.ladyhappy.postcreator.presentation

import com.egoriku.ladyhappy.postcreator.domain.predefined.CategoryModel
import com.egoriku.ladyhappy.postcreator.domain.predefined.SubCategory

data class ScreenState(
        val category: CategoryModel? = null,
        val subCategory: SubCategory? = null
)