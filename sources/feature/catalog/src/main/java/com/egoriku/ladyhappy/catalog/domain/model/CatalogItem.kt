package com.egoriku.ladyhappy.catalog.domain.model

data class CatalogItem(
        val itemLogoUrl: String,
        val itemName: String,
        val lastHatsImageUrl: List<String>
)