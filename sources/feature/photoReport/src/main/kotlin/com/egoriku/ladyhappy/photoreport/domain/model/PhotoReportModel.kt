package com.egoriku.ladyhappy.photoreport.domain.model

import com.egoriku.ladyhappy.mozaik.model.MozaikItem

data class PhotoReportModel(
    val date: String,
    val description: String,
    val images: List<MozaikItem>
)