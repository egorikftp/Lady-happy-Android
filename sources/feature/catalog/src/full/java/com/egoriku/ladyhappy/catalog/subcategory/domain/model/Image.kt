package com.egoriku.ladyhappy.catalog.subcategory.domain.model

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY

data class Image(
        val preview: String = EMPTY,
        val full: String = EMPTY
)