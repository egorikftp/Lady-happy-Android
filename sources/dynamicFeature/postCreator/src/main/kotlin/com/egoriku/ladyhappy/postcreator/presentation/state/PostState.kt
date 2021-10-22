package com.egoriku.ladyhappy.postcreator.presentation.state

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType.ChooserState
import com.egoriku.ladyhappy.postcreator.domain.model.image.ImageSection

data class PostState(
    val title: String = EMPTY,
    val imagesSection: ImageSection = ImageSection(),
    val category: ChooserType.Category = ChooserType.Category(state = ChooserState.Initial),
    val subCategory: ChooserType.SubCategory? = null,
    val color: ChooserType.Color = ChooserType.Color(state = ChooserState.Initial),
    val creationDate: ChooserType.CreationDate = ChooserType.CreationDate(state = ChooserState.Initial),
) {

    val chooserState: List<ChooserType>
        get() = listOfNotNull(category, subCategory, color, creationDate)
}