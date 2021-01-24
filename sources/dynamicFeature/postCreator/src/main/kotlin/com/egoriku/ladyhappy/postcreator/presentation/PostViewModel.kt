package com.egoriku.ladyhappy.postcreator.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.egoriku.ladyhappy.postcreator.presentation.state.ScreenState
import com.egoriku.ladyhappy.postcreator.presentation.state.UploadEvents
import com.egoriku.ladyhappy.ui.date.ddMMMyyyy
import com.egoriku.ladyhappy.ui.date.year
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class PostViewModel(
        private val uploadImagesUseCase: UploadImagesUseCase,
        private val publishPostUseCase: PublishPostUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _screenState

    private val _publishButtonAvailability = MutableStateFlow(false)
    val publishButtonAvailability: StateFlow<Boolean> = _publishButtonAvailability

    private val _uploadEvents = MutableSharedFlow<UploadEvents>()
    val uploadEvents: SharedFlow<UploadEvents> = _uploadEvents

    fun processImageResult(list: List<Uri>) {
        val images = _screenState.value.imagesSection.images
        val newImages = list.map { ImageItem(uri = it) }

        _screenState.value = _screenState.value.copy(
                imagesSection = ImageSection(images + newImages)
        )

        validateState()
    }

    fun removeAttachedImage(item: ImageItem) {
        val images = _screenState.value.imagesSection.images.toMutableList()
        images.remove(item)

        _screenState.value = _screenState.value.copy(imagesSection = ImageSection(images))

        validateState()
    }

    fun setTitle(text: String) {
        _screenState.value = _screenState.value.copy(title = text)

        validateState()
    }

    fun setCategory(category: String) {
        val categoryModel: CategoryModel = requireNotNull(PredefinedData.allCategories.find {
            it.name == category
        })

        _screenState.value = _screenState.value.copy(
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
        val subCategoryModel = requireNotNull(PredefinedData.allSubCategories
                .filter { it.categoryId == _screenState.value.category.categoryId }
                .find { it.name == subCategory }
        )

        _screenState.value = _screenState.value.copy(
                subCategory = ChooserType.SubCategory(
                        title = subCategoryModel.name,
                        state = ChooserState.Selected,
                        categoryId = subCategoryModel.categoryId,
                        subCategoryId = subCategoryModel.subCategoryId
                )
        )

        validateState()
    }

    fun setColor(id: Int) {
        val colorModel: ColorModel = requireNotNull(PredefinedData.colors.find {
            it.colorId == id
        })

        _screenState.value = _screenState.value.copy(
                color = ChooserType.Color(
                        title = colorModel.name,
                        state = ChooserState.Selected,
                        colorId = colorModel.colorId
                )
        )

        validateState()
    }

    fun setDate(dateInMillis: Long) {
        val date = Date(dateInMillis)

        _screenState.value = _screenState.value.copy(
                releaseDate = ChooserType.ReleaseDate(
                        date = date,
                        title = date.ddMMMyyyy(),
                        state = ChooserState.Selected
                )
        )

        validateState()
    }

    fun resetByChooserType(type: ChooserType) {
        _screenState.value = when (type) {
            is ChooserType.Category -> {
                _screenState.value.copy(
                        category = ChooserType.Category(state = ChooserState.Initial),
                        subCategory = null
                )
            }
            is ChooserType.SubCategory -> {
                _screenState.value.copy(subCategory = ChooserType.SubCategory(
                        state = ChooserState.Initial,
                        categoryId = _screenState.value.category.categoryId
                ))
            }
            is ChooserType.Color -> {
                _screenState.value.copy(color = ChooserType.Color(state = ChooserState.Initial))
            }
            is ChooserType.ReleaseDate -> {
                _screenState.value.copy(releaseDate = ChooserType.ReleaseDate(state = ChooserState.Initial))
            }
        }
    }

    fun publishPost() {
        viewModelScope.launch {
            _uploadEvents.emit(UploadEvents.InProgress)

            val state = _screenState.value
            val categoryId = state.category.categoryId
            val releaseDate = requireNotNull(state.releaseDate.date)

            val images = uploadImagesUseCase(
                    UploadImagesParams(
                            images = state.imagesSection.images,
                            category = categoryId,
                            year = releaseDate.year()

                    )
            ).successOr(emptyList())

            if (images.isEmpty()) {
                _uploadEvents.emit(UploadEvents.Error)

                return@launch
            }

            val subCategoryId = requireNotNull(state.subCategory).subCategoryId
            val colorId = state.color.colorId

            val resultOf = publishPostUseCase(
                    parameters = UploadEntity(
                            images = images,
                            title = state.title,
                            categoryId = categoryId,
                            subCategoryId = subCategoryId,
                            colorId = colorId,
                            date = Timestamp(releaseDate)
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
            state.title.isEmpty() -> false
            state.imagesSection.images.isEmpty() || state.imagesSection.images.size > 10 -> false
            state.category.state == ChooserState.Initial -> false
            state.subCategory == null || state.subCategory.state == ChooserState.Initial -> false
            state.color.state == ChooserState.Initial -> false
            state.releaseDate.date == null -> false
            else -> true
        }
    }
}
