package com.egoriku.ladyhappy.postcreator.presentation

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.predefined.PredefinedData

class PostViewModel(
        androidApplication: Application
) : AndroidViewModel(androidApplication) {

    private val _images: MutableLiveData<List<ImageItem>> = MutableLiveData()
    private val _screenState: MutableLiveData<ScreenState> = MutableLiveData(ScreenState())

    val images: LiveData<List<ImageItem>> = _images
    val screenState: LiveData<ScreenState> = _screenState

    init {
        _images.value = emptyList()
    }

    fun processImageResult(list: List<Uri>) {
        val images = _images.value?.plus(
                list.map {
                    ImageItem(uri = it)
                }
        )

        _images.value = images
    }

    fun removeAttachedImage(item: ImageItem) {
        val images = _images.value?.toMutableList() ?: mutableListOf()
        images.remove(item)

        _images.value = images
    }

    fun updateCategory(category: String?) {
        val model = PredefinedData.allCategories.find {
            it.name == category
        }

        _screenState.value = _screenState.value?.copy(category = model)
    }
}
