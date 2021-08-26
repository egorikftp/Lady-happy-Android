package com.egoriku.ladyhappy.postcreator.presentation

import android.app.Application
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.dateformat.ddMMMyyyy
import com.egoriku.ladyhappy.core.dateformat.year
import com.egoriku.ladyhappy.extensions.imageCreationDate
import com.egoriku.ladyhappy.network.ResultOf
import com.egoriku.ladyhappy.network.successOr
import com.egoriku.ladyhappy.postcreator.data.entity.UploadEntity
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType
import com.egoriku.ladyhappy.postcreator.domain.model.chooser.ChooserType.ChooserState
import com.egoriku.ladyhappy.postcreator.domain.model.image.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.model.image.ImageSection
import com.egoriku.ladyhappy.postcreator.domain.model.image.UploadImagesParams
import com.egoriku.ladyhappy.postcreator.domain.predefined.HatColor
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.domain.usecase.PublishPostUseCase
import com.egoriku.ladyhappy.postcreator.domain.usecase.UploadImagesUseCase
import com.egoriku.ladyhappy.postcreator.presentation.state.DialogEvent
import com.egoriku.ladyhappy.postcreator.presentation.state.Effect
import com.egoriku.ladyhappy.postcreator.presentation.state.PostState
import com.egoriku.ladyhappy.postcreator.presentation.state.ScreenState
import com.egoriku.ladyhappy.postcreator.presentation.state.ScreenState.Uploading.Stage
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

const val MAX_IMAGES_SIZE = 10

class PostViewModel(
    application: Application,
    private val uploadImagesUseCase: UploadImagesUseCase,
    private val publishPostUseCase: PublishPostUseCase,
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.None)
    val uiState = _uiState.asStateFlow()

    private val _postState = MutableStateFlow(PostState())
    val postState = _postState.asStateFlow()

    private val _publishButtonAvailability = MutableStateFlow(false)
    val publishButtonAvailability = _publishButtonAvailability.asStateFlow()

    private val _dialogEffects = MutableSharedFlow<DialogEvent>()
    val dialogEffects = _dialogEffects.asSharedFlow()

    private val _effects = MutableSharedFlow<Effect>()
    val effects = _effects.asSharedFlow()

    private val _currentPostState
        get() = _postState.value

    private val subCategoryId: Int
        get() = requireNotNull(
            _currentPostState.chooserState
                .filterIsInstance<ChooserType.SubCategory>()
                .firstOrNull()
                ?.categoryId
        )

    init {
        viewModelScope.launch {
            _uiState.emit(ScreenState.Loading)
            // TODO: 26.08.21 Load config from firebase
            _uiState.emit(ScreenState.CreatePost)
        }
    }

    fun processEffect(effect: Effect) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }

    fun openDialog(type: ChooserType) {
        val dialogEvent = when (type) {
            is ChooserType.Category -> DialogEvent.Category(
                data = PredefinedData.getCategoriesNames()
            )
            is ChooserType.SubCategory -> DialogEvent.SubCategory(
                data = PredefinedData.getSubCategoriesNames(subCategoryId)
            )
            is ChooserType.Color -> DialogEvent.Color
            is ChooserType.CreationDate -> DialogEvent.Date
        }

        viewModelScope.launch {
            _dialogEffects.emit(dialogEvent)
        }
    }

    fun processImageResult(list: List<Uri>) {
        val images = _currentPostState.imagesSection.images
        val newImages = list.map { ImageItem(uri = it) }

        _postState.value = _currentPostState.copy(
            imagesSection = ImageSection(images + newImages)
        )

        validateState()

        tryToExtractImageDate()
    }

    fun removeAttachedImage(item: ImageItem) {
        val images = _currentPostState.imagesSection.images.toMutableList()
        images.remove(item)

        _postState.value = _currentPostState.copy(imagesSection = ImageSection(images))

        validateState()
    }

    fun setTitle(text: String) {
        _postState.value = _currentPostState.copy(title = text)

        validateState()
    }

    fun setCategory(category: String) {
        resetDialogEffects()

        val categoryModel = requireNotNull(
            PredefinedData.findCategory(name = category)
        )

        _postState.value = _currentPostState.copy(
            category = ChooserType.Category(
                title = categoryModel.name,
                state = ChooserState.Selected,
                categoryId = categoryModel.categoryId
            ),
            subCategory = ChooserType.SubCategory(
                state = ChooserState.Initial,
                categoryId = categoryModel.categoryId
            )
        )

        validateState()
    }

    fun resetDialogEffects() {
        viewModelScope.launch {
            _dialogEffects.emit(DialogEvent.None)
        }
    }

    fun setSubCategory(subCategoryName: String) {
        resetDialogEffects()

        val subCategory = requireNotNull(
            PredefinedData.findSubCategory(
                categoryId = _currentPostState.category.categoryId,
                name = subCategoryName
            )
        )

        _postState.value = _currentPostState.copy(
            subCategory = ChooserType.SubCategory(
                title = subCategory.name,
                state = ChooserState.Selected,
                categoryId = subCategory.categoryId,
                subCategoryId = subCategory.subCategoryId
            )
        )

        validateState()
    }

    fun setColor(hatColors: List<HatColor>) {
        resetDialogEffects()

        _postState.value = _currentPostState.copy(
            color = ChooserType.Color(
                title = hatColors.joinToString { it.name },
                state = ChooserState.Selected,
                colors = hatColors.map { it.colorId }
            )
        )

        validateState()
    }

    fun setDate(dateInMillis: Long) {
        resetDialogEffects()

        val date = Date(dateInMillis)

        _postState.value = _currentPostState.copy(
            creationDate = ChooserType.CreationDate(
                date = date,
                title = date.ddMMMyyyy(),
                state = ChooserState.Selected
            )
        )

        validateState()
    }

    fun resetByChooserType(type: ChooserType) {
        _postState.value = when (type) {
            is ChooserType.Category -> _currentPostState.copy(
                category = ChooserType.Category(state = ChooserState.Initial),
                subCategory = null
            )
            is ChooserType.SubCategory -> _currentPostState.copy(
                subCategory = ChooserType.SubCategory(
                    state = ChooserState.Initial,
                    categoryId = _currentPostState.category.categoryId
                )
            )
            is ChooserType.Color -> _currentPostState.copy(
                color = ChooserType.Color(state = ChooserState.Initial)
            )
            is ChooserType.CreationDate -> _currentPostState.copy(
                creationDate = ChooserType.CreationDate(state = ChooserState.Initial)
            )
        }
    }

    fun publishPost() {
        viewModelScope.launch {
            _uiState.emit(ScreenState.Uploading(stage = Stage.Photos))

            val state = _currentPostState
            val categoryId = state.category.categoryId
            val subCategoryId = requireNotNull(state.subCategory).subCategoryId
            val colorId = state.color.colors
            val creationDate = requireNotNull(state.creationDate.date)

            val images = uploadImagesUseCase(
                parameters = UploadImagesParams(
                    images = state.imagesSection.images,
                    category = categoryId,
                    year = creationDate.year()

                )
            ).successOr(emptyList())

            if (images.isEmpty()) {
                _effects.emit(Effect.UploadError)
                _uiState.emit(ScreenState.CreatePost)

                return@launch
            }

            _uiState.emit(ScreenState.Uploading(stage = Stage.Post))

            val resultOf = publishPostUseCase(
                parameters = UploadEntity(
                    images = images,
                    title = state.title,
                    categoryId = categoryId,
                    subCategoryId = subCategoryId,
                    colors = colorId,
                    date = Timestamp(creationDate)
                )
            )

            when (resultOf) {
                is ResultOf.Success -> _effects.emit(Effect.UploadSuccess)
                is ResultOf.Failure -> _effects.emit(Effect.UploadError)
            }
        }
    }

    private fun validateState() {
        val state = _postState.value

        _publishButtonAvailability.value = when {
            state.imagesSection.images.isEmpty() || state.imagesSection.images.size > MAX_IMAGES_SIZE -> false
            state.category.state == ChooserState.Initial -> false
            state.subCategory == null || state.subCategory.state == ChooserState.Initial -> false
            state.color.state == ChooserState.Initial -> false
            state.creationDate.date == null -> false
            else -> true
        }
    }

    private fun tryToExtractImageDate() {
        viewModelScope.launch {
            val images = _currentPostState.imagesSection.images

            if (images.isNotEmpty()) {
                val uri = images.first().uri

                withContext(Dispatchers.IO) {
                    getApplication<Application>().contentResolver.openInputStream(uri)?.use {
                        setDate(ExifInterface(it).imageCreationDate)
                    }
                }
            }
        }
    }
}
