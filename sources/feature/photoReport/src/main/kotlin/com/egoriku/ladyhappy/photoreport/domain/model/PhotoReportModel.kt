package com.egoriku.ladyhappy.photoreport.domain.model

data class PhotoReportModel(
        val date: String,
        val description: String,
        val images: List<String>
)