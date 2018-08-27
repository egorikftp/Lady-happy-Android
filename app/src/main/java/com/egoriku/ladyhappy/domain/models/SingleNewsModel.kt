package com.egoriku.ladyhappy.domain.models

import com.egoriku.featureactivitymain.common.Constants

data class SingleNewsModel(
        val date: String = Constants.EMPTY,
        val description: String = Constants.EMPTY,
        val images: List<String> = listOf()
)

data class NewsModel(val news: List<SingleNewsModel> = listOf())