package com.egoriku.ladyhappy.postcreator.presentation.state

sealed class DialogEvent {
    object None : DialogEvent()
    data class Category(val data: List<String>) : DialogEvent()
    data class SubCategory(val data: List<String>) : DialogEvent()
    object Color : DialogEvent()
    object Date : DialogEvent()
}
