package com.egoriku.ladyhappy.domain.models

import com.egoriku.corelib_kt.Constants

data class CategoryModel(
        var id: Int = 0,
        var title: String = Constants.EMPTY,
        var imageUrl: String = Constants.EMPTY
)