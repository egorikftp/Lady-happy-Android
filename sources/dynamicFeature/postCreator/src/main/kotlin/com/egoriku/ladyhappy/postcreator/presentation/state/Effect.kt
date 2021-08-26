package com.egoriku.ladyhappy.postcreator.presentation.state

sealed class Effect {

    object UploadError : Effect()
    object UploadSuccess : Effect()
    object DemoMode : Effect()
}
