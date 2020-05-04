package com.egoriku.ladyhappy.postcreator.presentation

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egoriku.ladyhappy.postcreator.domain.model.ImageItem
import com.egoriku.ladyhappy.postcreator.domain.predefined.CategoryModel

class PostViewModel(
        androidApplication: Application
) : AndroidViewModel(androidApplication) {

    private val _images: MutableLiveData<List<ImageItem>> = MutableLiveData()

    val images: LiveData<List<ImageItem>> = _images

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

    fun updateCategory(category: CategoryModel) {

    }
}
