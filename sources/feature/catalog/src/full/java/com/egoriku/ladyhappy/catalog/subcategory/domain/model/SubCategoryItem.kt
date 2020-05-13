package com.egoriku.ladyhappy.catalog.subcategory.domain.model

import com.egoriku.mozaik.model.MozaikImageItem

data class SubCategoryItem(
        val name: String,
        val isPopular: Boolean,
        val count: Int,
        val images: List<MozaikImageItem>
)