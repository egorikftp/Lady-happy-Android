package com.egoriku.ladyhappy.postcreator.presentation.ui.images

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.egoriku.ladyhappy.postcreator.presentation.ui.CircleIcon

@Composable
fun AddNewImage(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .clip(shape = RoundedCornerShape(size = 4.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        CircleIcon(
            size = 50.dp,
            imageVector = Icons.Filled.Add,
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
            onClick = onClick
        )
    }
}