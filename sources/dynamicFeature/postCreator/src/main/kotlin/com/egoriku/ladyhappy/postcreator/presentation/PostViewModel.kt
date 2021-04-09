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
import com.egoriku.ladyhappy.postcreator.domain.predefined.CategoryModel
import com.egoriku.ladyhappy.postcreator.domain.predefined.ColorModel
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.domain.usecase.PublishPostUseCase
import com.egoriku.ladyhappy.postcreator.domain.usecase.UploadImagesUseCase
import com.egoriku.ladyhappy.postcreator.presentation.common.MAX_IMAGES_SIZE
import com.egoriku.ladyhappy.postcreator.presentation.state.ScreenState
import com.egoriku.ladyhappy.postcreator.presentation.state.UploadEvents
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class PostViewModel(
        application: Application,
        private val uploadImagesUseCase: UploadImagesUseCase,
        private val publishPostUseCase: PublishPostUseCase,
) : AndroidViewModel(application) {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _screenState

    private val _publishButtonAvailability = MutableStateFlow(false)
    val publishButtonAvailability: StateFlow<Boolean> = _publishButtonAvailability

    private val _uploadEvents = MutableSharedFlow<UploadEvents>()
    val uploadEvents: SharedFlow<UploadEvents> = _uploadEvents

    private val _currentScreenState
        get() = _screenState.value

    fun processImageResult(list: List<Uri>) {
        val images = _currentScreenState.imagesSection.images
        val newImages = list.map { ImageItem(uri = it) }

        _screenState.value = _currentScreenState.copy(
                imagesSection = ImageSection(images + newImages)
        )

        validateState()

        tryToExtractImageDate()
    }

    fun removeAttachedImage(item: ImageItem) {
        val images = _currentScreenState.imagesSection.images.toMutableList()
        images.remove(item)

        _screenState.value = _currentScreenState.copy(imagesSection = ImageSection(images))

        validateState()
    }

    fun setTitle(text: String) {
        _screenState.value = _currentScreenState.copy(title = text)

        validateState()
    }

    fun setCategory(category: String) {
        val categoryModel: CategoryModel = requireNotNull(
                PredefinedData.allCategories.find {
                    it.name == category
                }
        )

        _screenState.value = _currentScreenState.copy(
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

    fun setSubCategory(subCategory: String?) {
        val subCategoryModel = requireNotNull(
                PredefinedData.allSubCategories
                        .filter { it.categoryId == _currentScreenState.category.categoryId }
                        .find { it.name == subCategory }
        )

        _screenState.value = _currentScreenState.copy(
                subCategory = ChooserType.SubCategory(
                        title = subCategoryModel.name,
                        state = ChooserState.Selected,
                        categoryId = subCategoryModel.categoryId,
                        subCategoryId = subCategoryModel.subCategoryId
                )
        )

        validateState()
    }

    fun setColor(colors: List<ColorModel>) {
        _screenState.value = _currentScreenState.copy(
                color = ChooserType.Color(
                        title = colors.joinToString { it.name },
                        state = ChooserState.Selected,
                        colors = colors.map { it.colorId }
                )
        )

        validateState()
    }

    fun setDate(dateInMillis: Long) {
        val date = Date(dateInMillis)

        _screenState.value = _currentScreenState.copy(
                creationDate = ChooserType.CreationDate(
                        date = date,
                        title = date.ddMMMyyyy(),
                        state = ChooserState.Selected
                )
        )

        validateState()
    }

    fun resetByChooserType(type: ChooserType) {
        _screenState.value = when (type) {
            is ChooserType.Category -> _currentScreenState.copy(
                    category = ChooserType.Category(state = ChooserState.Initial),
                    subCategory = null
            )
            is ChooserType.SubCategory -> _currentScreenState.copy(
                    subCategory = ChooserType.SubCategory(
                            state = ChooserState.Initial,
                            categoryId = _currentScreenState.category.categoryId
                    )
            )
            is ChooserType.Color -> _currentScreenState.copy(
                    color = ChooserType.Color(state = ChooserState.Initial)
            )
            is ChooserType.CreationDate -> _currentScreenState.copy(
                    creationDate = ChooserType.CreationDate(state = ChooserState.Initial)
            )
        }
    }

    fun publishPost() {
        viewModelScope.launch {
            _uploadEvents.emit(UploadEvents.InProgress)

            val state = _screenState.value
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
                _uploadEvents.emit(UploadEvents.Error)

                return@launch
            }

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
                is ResultOf.Success -> _uploadEvents.emit(UploadEvents.Success)
                is ResultOf.Failure -> _uploadEvents.emit(UploadEvents.Error)
            }
        }
    }

    private fun validateState() {
        val state = _screenState.value

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
            val images = _currentScreenState.imagesSection.images

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
