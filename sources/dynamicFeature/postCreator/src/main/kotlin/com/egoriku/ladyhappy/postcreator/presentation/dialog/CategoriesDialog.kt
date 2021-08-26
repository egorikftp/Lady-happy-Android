package com.egoriku.ladyhappy.postcreator.presentation.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.listItemsSingleChoice
import com.vanpra.composematerialdialogs.title

@Composable
fun CategoriesDialog(
    titleId: Int,
    categories: List<String>,
    onSelect: (String) -> Unit,
    onCancel: () -> Unit
) {
    val dialog = remember { MaterialDialog() }

    dialog.build(content = {
        title(res = titleId)
        listItemsSingleChoice(categories) {
            onSelect(categories[it])
        }
    }, buttons = {
        negativeButton(stringResource(id = android.R.string.cancel), onClick = onCancel)
        positiveButton(stringResource(id = android.R.string.ok))
    })

    dialog.show()
}