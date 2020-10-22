package com.egoriku.ladyhappy.postcreator.presentation.model

sealed class Chooser(
        open val type: Type,
        open val optionalData: Int
) {
    data class Initial(
            override val type: Type,
            override val optionalData: Int = -1
    ) : Chooser(type, optionalData)

    data class Selected(
            override val type: Type,
            val primary: String,
            override val optionalData: Int = -1
    ) : Chooser(type, optionalData)
}