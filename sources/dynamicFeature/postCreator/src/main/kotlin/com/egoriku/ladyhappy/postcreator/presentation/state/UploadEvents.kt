package com.egoriku.ladyhappy.postcreator.presentation.state

sealed class UploadEvents {

    object InProgress : UploadEvents()
    object Error : UploadEvents()
    object Success : UploadEvents()
}