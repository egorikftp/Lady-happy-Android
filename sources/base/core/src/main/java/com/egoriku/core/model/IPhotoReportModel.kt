package com.egoriku.core.model

interface IPhotoReportModel {

    val date: String

    val description: String

    val images: List<String>
}