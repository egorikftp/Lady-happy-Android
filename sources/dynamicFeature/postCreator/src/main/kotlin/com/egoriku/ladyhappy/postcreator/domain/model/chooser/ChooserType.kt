package com.egoriku.ladyhappy.postcreator.domain.model.chooser

import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import java.util.*

sealed class ChooserType {

    sealed class ChooserState {
        object Initial : ChooserState()
        object Selected : ChooserState()
    }

    abstract val state: ChooserState
    abstract val chooserHint: String
    abstract val title: String

    data class Category(
            override val title: String = EMPTY,
            override val chooserHint: String = "Категория",
            override val state: ChooserState,
            val categoryId: Int = -1,
    ) : ChooserType()

    data class SubCategory(
            override val title: String = EMPTY,
            override val chooserHint: String = "Подкатегория",
            override val state: ChooserState,
            val subCategoryId: Int = -1,
            val categoryId: Int = -1,
    ) : ChooserType()

    data class Color(
            override val title: String = EMPTY,
            override val chooserHint: String = "Цвет",
            override val state: ChooserState,
            val colorId: Int = -1,
    ) : ChooserType()

    data class ReleaseDate(
            override val title: String = EMPTY,
            override val chooserHint: String = "Дата создания",
            override val state: ChooserState,
            val date: Date? = null
    ) : ChooserType()
}
