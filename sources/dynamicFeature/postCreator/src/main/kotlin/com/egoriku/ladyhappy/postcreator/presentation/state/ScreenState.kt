package com.egoriku.ladyhappy.postcreator.presentation.state

sealed class ScreenState {

    object None : ScreenState()
    object Loading : ScreenState()
    object CreatePost : ScreenState()
    data class Uploading(val stage: Stage) : ScreenState() {
        enum class Stage {
            Photos,
            Post
        }
    }
}