package com.egoriku.ladyhappy.postcreator.domain.model.chooser

import androidx.annotation.StringRes
import com.egoriku.ladyhappy.extensions.common.Constants.EMPTY
import java.util.*
import com.egoriku.ladyhappy.localization.R as R_localization

sealed class ChooserType {

    sealed class ChooserState {
        object Initial : ChooserState()
        object Selected : ChooserState()
    }

    abstract val state: ChooserState
    abstract val hintResId: Int
    abstract val title: String

    data class Category(
            override val title: String = EMPTY,
            @StringRes
            override val hintResId: Int = R_localization.string.post_creator_chooser_hint_categories,
            override val state: ChooserState,
            val categoryId: Int = -1,
    ) : ChooserType()

    data class SubCategory(
            override val title: String = EMPTY,
            @StringRes
            override val hintResId: Int = R_localization.string.post_creator_chooser_hint_subcategories,
            override val state: ChooserState,
            val subCategoryId: Int = -1,
            val categoryId: Int = -1,
    ) : ChooserType()

    data class Color(
            override val title: String = EMPTY,
            @StringRes
            override val hintResId: Int = R_localization.string.post_creator_chooser_hint_color,
            override val state: ChooserState,
            val colors: List<Int> = emptyList(),
    ) : ChooserType()

    data class CreationDate(
            override val title: String = EMPTY,
            @StringRes
            override val hintResId: Int = R_localization.string.post_creator_chooser_hint_creation_date,
            override val state: ChooserState,
            val date: Date? = null,
    ) : ChooserType()
}
