package com.egoriku.ladyhappy.postcreator.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
fun CircleIcon(
    modifier: Modifier = Modifier,
    size: Dp,
    imageVector: ImageVector,
    backgroundColor: Color,
    iconTint: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues()
) {
    Box(
        modifier = modifier
            .padding(paddingValues)
            .size(size = size)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = iconTint
        )
    }
}