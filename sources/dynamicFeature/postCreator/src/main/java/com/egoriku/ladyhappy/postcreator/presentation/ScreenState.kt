package com.egoriku.ladyhappy.postcreator.presentation

import com.egoriku.ladyhappy.postcreator.presentation.model.Chooser
import com.egoriku.ladyhappy.postcreator.presentation.model.ImageSection

data class ScreenState(
        var imagesSection: ImageSection = ImageSection(),
        val chooser: List<Chooser> = listOf()
)