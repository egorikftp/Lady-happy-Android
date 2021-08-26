package com.egoriku.ladyhappy.postcreator.presentation.ui.images

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.egoriku.ladyhappy.compose.ui.NetworkImage
import com.egoriku.ladyhappy.compose.ui.RealWhite
import com.egoriku.ladyhappy.postcreator.presentation.ui.CircleIcon

@Composable
fun ImageRowItem(uri: Uri, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(shape = RoundedCornerShape(size = 4.dp)),
        contentAlignment = Alignment.TopEnd
    ) {
        NetworkImage(
            modifier = Modifier.fillMaxSize(),
            url = uri.toString()
        )
        CircleIcon(
            size = 40.dp,
            imageVector = Icons.Filled.Close,
            backgroundColor = RealWhite.copy(alpha = 0.1f),
            iconTint = Color.White,
            onClick = onRemove,
            paddingValues = PaddingValues(top = 4.dp, end = 4.dp)
        )
    }
}