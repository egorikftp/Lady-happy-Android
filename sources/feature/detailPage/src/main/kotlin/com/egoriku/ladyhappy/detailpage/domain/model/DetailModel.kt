package com.egoriku.ladyhappy.detailpage.domain.model

import com.egoriku.ladyhappy.mozaik.model.MozaikItem

data class DetailModel(
        val images: List<MozaikItem>,
        val description: String,
        val date: String
)