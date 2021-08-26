package com.egoriku.ladyhappy.postcreator.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.egoriku.ladyhappy.postcreator.R
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType
import com.egoriku.ladyhappy.postcreator.presentation.dialog.CategoriesDialog
import com.egoriku.ladyhappy.postcreator.presentation.state.DialogEvent
import com.egoriku.ladyhappy.postcreator.presentation.state.Effect
import com.egoriku.ladyhappy.postcreator.presentation.ui.Chooser
import com.egoriku.ladyhappy.postcreator.presentation.ui.images.ImageRow
import kotlinx.coroutines.flow.collect

@Composable
fun PublishProductScreen(
    viewModel: PostViewModel,
    isDemoMode: Boolean,
    onBack: () -> Unit,
    openDatePicker: () -> Unit,
    openColorPicker: () -> Unit
) {
    val scrollState = rememberScrollState()

    val state by viewModel.postState.collectAsState()
    val publishAvailability by viewModel.publishButtonAvailability.collectAsState()

    var dialogEffect by remember { mutableStateOf<DialogEvent>(DialogEvent.None) }

    when (val effect = dialogEffect) {
        is DialogEvent.Category -> CategoriesDialog(
            titleId = R.string.post_creator_categories_dialog_title,
            categories = effect.data,
            onSelect = viewModel::setCategory,
            onCancel = viewModel::resetDialogEffects
        )
        is DialogEvent.SubCategory -> CategoriesDialog(
            titleId = R.string.post_creator_subcategories_categories_dialog_title,
            categories = effect.data,
            onSelect = viewModel::setSubCategory,
            onCancel = viewModel::resetDialogEffects
        )
        is DialogEvent.Date -> {
            openDatePicker()
            viewModel.resetDialogEffects()
        }
        DialogEvent.Color -> {
            openColorPicker()
            viewModel.resetDialogEffects()
        }
        DialogEvent.None -> Unit
    }

    LaunchedEffect(viewModel) {
        viewModel.dialogEffects.collect { dialogEffect = it }
    }

    val getImagesContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = viewModel::processImageResult
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            contentColor = MaterialTheme.colors.onBackground,
            title = { Text(text = stringResource(id = R.string.post_creator_screen_title)) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.Close, contentDescription = null)
                }
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            value = state.title,
            singleLine = true,
            onValueChange = { viewModel.setTitle(it) },
            label = {
                Text(text = stringResource(R.string.post_creator_input_hint))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        ImageRow(
            header = stringResource(R.string.post_creator_images_section_title),
            onAddClick = { getImagesContract.launch("image/*") },
            images = state.imagesSection.images,
            onRemove = { viewModel.removeAttachedImage(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.chooserState.forEach {
            Chooser(
                selectedInfo = it.title,
                hint = stringResource(id = it.hintResId),
                infoSelected = it.state is ChooserType.ChooserState.Initial,
                onClick = { viewModel.openDialog(it) },
                onClear = { viewModel.resetByChooserType(it) }
            )
        }

        Button(
            enabled = publishAvailability,
            onClick = {
                when {
                    isDemoMode -> viewModel.processEffect(Effect.DemoMode)
                    else -> viewModel.publishPost()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.post_creator_publish_button_text),
                style = MaterialTheme.typography.button
            )
        }
    }
}