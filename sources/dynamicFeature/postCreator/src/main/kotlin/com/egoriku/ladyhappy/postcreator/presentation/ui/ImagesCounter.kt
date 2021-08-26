package com.egoriku.ladyhappy.postcreator.presentation.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.presentation.MAX_IMAGES_SIZE

@Composable
fun ImagesCounter(size: Int) {
    val fontWeight = when {
        size > MAX_IMAGES_SIZE -> FontWeight.Bold
        else -> FontWeight.Normal
    }
    val textColor = when {
        size > MAX_IMAGES_SIZE -> MaterialTheme.colors.error
        else -> MaterialTheme.colors.onSurface
    }
    val text = stringResource(id = R.string.post_creator_images_count, size)

    Text(
        text = text,
        color = textColor,
        style = MaterialTheme.typography.body1,
        fontWeight = fontWeight
    )
}