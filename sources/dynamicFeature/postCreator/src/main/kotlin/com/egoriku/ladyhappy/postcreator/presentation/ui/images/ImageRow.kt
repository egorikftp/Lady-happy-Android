package com.egoriku.ladyhappy.postcreator.presentation.ui.images

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.egoriku.ladyhappy.postcreator.domain.model.image.ImageItem
import com.egoriku.ladyhappy.postcreator.presentation.ui.ImagesCounter

@Composable
fun ImageRow(
    header: String,
    onAddClick: () -> Unit,
    onRemove: (ImageItem) -> Unit,
    images: List<ImageItem>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = header, style = MaterialTheme.typography.body1)
            ImagesCounter(size = images.size)
        }
        LazyRow(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AddNewImage(onClick = onAddClick)
            }
            items(images) {
                ImageRowItem(
                    uri = it.uri,
                    onRemove = { onRemove(it) }
                )
            }
        }
    }
}