package com.egoriku.ladyhappy.catalog.subcategory.domain.model

import com.egoriku.ladyhappy.mozaik.model.MozaikItem

data class SubCategoryItem(
        val name: String,
        val isPopular: Boolean,
        val publishedCount: Int,
        val images: List<MozaikItem>
)