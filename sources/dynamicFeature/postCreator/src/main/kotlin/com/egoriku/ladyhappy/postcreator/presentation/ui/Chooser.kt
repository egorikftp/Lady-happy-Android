package com.egoriku.ladyhappy.postcreator.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chooser(
    selectedInfo: String,
    hint: String,
    infoSelected: Boolean,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            if (infoSelected) {
                Text(text = hint, style = MaterialTheme.typography.body1)
            } else {
                Text(text = hint, style = MaterialTheme.typography.caption)
                Text(text = selectedInfo, style = MaterialTheme.typography.body1)
            }
        }

        if (infoSelected) {
            NonClickableImageButton(modifier = Modifier.padding(8.dp)) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null
                )
            }
        } else {
            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = onClear
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null
                )
            }
        }
    }
}