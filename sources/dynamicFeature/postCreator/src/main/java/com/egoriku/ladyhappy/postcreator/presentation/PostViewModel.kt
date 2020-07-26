package com.egoriku.ladyhappy.postcreator.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.extensions.valueOrThrow
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData

class PostViewModel : ViewModel() {

    private val _images: MutableLiveData<List<ImageItem>> = MutableLiveData(emptyList())
    private val _screenState: MutableLiveData<ScreenState> = MutableLiveData(ScreenState())

    val images: LiveData<List<ImageItem>> = _images
    val screenState: LiveData<ScreenState> = _screenState

    init {
        _images.value = emptyList()
    }

    fun processImageResult(list: List<Uri>) {
        val images = _images.valueOrThrow()
                .plus(list.map { ImageItem(uri = it) })

        _images.value = images
    }

    fun removeAttachedImage(item: ImageItem) {
        val images = _images.valueOrThrow().toMutableList()
        images.remove(item)

        _images.value = images
    }

    fun updateCategory(category: String?) {
        val model = PredefinedData.allCategories.find {
            it.name == category
        }

        _screenState.value = _screenState
                .valueOrThrow()
                .copy(category = model)
    }

    fun updateSubCategory(subCategory: String?) {
        val model = PredefinedData.allSubCategories
                .filter { it.categoryId == _screenState.valueOrThrow().category?.categoryId }
                .find { it.name == subCategory }

        _screenState.value = _screenState
                .valueOrThrow()
                .copy(subCategory = model)
    }
}
