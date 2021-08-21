package com.egoriku.ladyhappy.adminconsole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.adminconsole.extension.setThemeContent

class AdminConsoleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = setThemeContent {
        Surface {
            ConsoleScreen()
        }
    }
}

@Preview
@Composable
fun ConsoleScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        FeatureItem(onClick = {}, name = "Publish news")
        FeatureItem(onClick = {}, name = "Publish product")
        FeatureItem(onClick = {}, name = "Manage users and permissions")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeatureItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    name: String
) {
    Card(
        elevation = 3.dp,
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp)
            .padding(all = 16.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = name, style = MaterialTheme.typography.h6)
        }
    }
}