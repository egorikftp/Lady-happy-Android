package com.egoriku.ladyhappy.postcreator.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.predefined.CategoryModel
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData
import com.egoriku.ladyhappy.postcreator.domain.predefined.SubCategoryModel
import com.egoriku.ladyhappy.postcreator.domain.usecase.UploadImagesUseCase
import com.egoriku.ladyhappy.postcreator.presentation.model.Chooser
import com.egoriku.ladyhappy.postcreator.presentation.model.ImageSection
import com.egoriku.ladyhappy.postcreator.presentation.model.Type
import kotlinx.coroutines.launch

class PostViewModel(
        private val uploadImagesUseCase: UploadImagesUseCase
) : ViewModel() {

    private val stateModifier = StateModifier()

    private var cache = ScreenState(
            chooser = listOf(
                    Chooser.Initial(type = Type.CATEGORY),
                    Chooser.Initial(type = Type.COLOR)
            ),
            imagesSection = ImageSection(emptyList())
    )

    private val _screenState: MutableLiveData<ScreenState> = MutableLiveData(cache)
    val screenState: LiveData<ScreenState> = _screenState

    fun processImageResult(list: List<Uri>) {
        val images = cache.imagesSection.images
        val newImages = list.map { ImageItem(uri = it) }

        cache = cache.copy(imagesSection = ImageSection(images + newImages))
        _screenState.value = cache
    }

    fun removeAttachedImage(item: ImageItem) {
        val images = cache.imagesSection.images.toMutableList()
        images.remove(item)

        cache = cache.copy(imagesSection = ImageSection(images))
        _screenState.value = cache
    }

    fun setCategory(category: String) {
        val categoryModel: CategoryModel = requireNotNull(PredefinedData.allCategories.find {
            it.name == category
        })

        val chooser = stateModifier.updateCategory(state = cache.chooser, category = categoryModel)
        cache = cache.copy(chooser = chooser)

        _screenState.value = cache
    }

    fun resetByType(type: Type) {
        val chooser = cache.chooser
                .map { chooser ->
                    when (chooser.type) {
                        type -> Chooser.Initial(type = type)
                        else -> chooser
                    }
                }.toMutableList().apply {
                    if (type == Type.CATEGORY) {
                        stateModifier.removeSubCategorySection(this)
                    }
                }

        cache = cache.copy(chooser = chooser)

        _screenState.value = cache
    }

    fun resetSubCategory() {
        val chooser = stateModifier.resetSubCategory(cache.chooser)
        cache = cache.copy(chooser = chooser)

        _screenState.value = cache
    }

    fun updateSubCategory(subCategory: String?) {
        val categoryId = cache.chooser
                .find { it.type == Type.SUBCATEGORY }
                ?.optionalData

        val model = requireNotNull(PredefinedData.allSubCategories
                .filter { it.categoryId == categoryId }
                .find { it.name == subCategory }
        )

        val chooser = stateModifier.updateSubCategory(cache.chooser, model)
        cache = cache.copy(chooser = chooser)

        _screenState.value = cache
    }

    fun publishPost() {
        viewModelScope.launch {
            // val uploadedImages = uploadImagesUseCase(_images.valueOrThrow())
        }
    }

    inner class StateModifier {

        fun updateCategory(state: List<Chooser>, category: CategoryModel): List<Chooser> = state
                .map { chooser ->
                    when (chooser.type) {
                        Type.CATEGORY -> Chooser.Selected(type = Type.CATEGORY, primary = category.name)
                        else -> chooser
                    }
                }.toMutableList().apply {
                    removeSubCategorySection(this)

                    add(Chooser.Initial(type = Type.SUBCATEGORY, optionalData = category.categoryId))
                }.also { list ->
                    list.sortBy { it.type.index }
                }

        fun updateSubCategory(state: List<Chooser>, subCategory: SubCategoryModel) =
                state.map { chooser ->
                    when (chooser.type) {
                        Type.SUBCATEGORY -> Chooser.Selected(
                                type = Type.SUBCATEGORY,
                                primary = subCategory.name,
                                optionalData = chooser.optionalData
                        )
                        else -> chooser
                    }
                }

        fun resetSubCategory(state: List<Chooser>) = state.map { chooser ->
            when (chooser.type) {
                Type.SUBCATEGORY -> Chooser.Initial(
                        type = Type.SUBCATEGORY,
                        optionalData = chooser.optionalData
                )
                else -> chooser
            }
        }

        fun removeSubCategorySection(state: MutableList<Chooser>) = state.removeSection()

        private fun MutableList<Chooser>.removeSection() {
            val element = find { it.type == Type.SUBCATEGORY }

            if (element != null) {
                removeAt(indexOf(element))
            }
        }
    }
}
