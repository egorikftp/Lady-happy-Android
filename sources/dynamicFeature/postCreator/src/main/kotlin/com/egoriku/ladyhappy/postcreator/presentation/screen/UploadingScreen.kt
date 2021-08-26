package com.egoriku.ladyhappy.postcreator.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.egoriku.ladyhappy.postcreator.presentation.state.ScreenState
import com.egoriku.ladyhappy.postcreator.presentation.state.ScreenState.Uploading.Stage

@Composable
fun UploadingScreen(uiState: ScreenState.Uploading) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState.stage) {
            Stage.Photos -> Text(text = "Processing photos")
            Stage.Post -> Text(text = "Uploading post")
        }
    }
}