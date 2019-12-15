package com.egoriku.ladyhappy.catalog.subcategory.domain.model

data class SubCategoryItem(
        val headerImage: Image,
        val itemName: String,
        val lastHats: List<HatModel>
)