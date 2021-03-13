package com.egoriku.ladyhappy.catalog.edit.presentation

import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.catalog.edit.domain.ILoadSubCategoryUseCase
import com.egoriku.ladyhappy.catalog.edit.domain.IUploadSubCategoryUseCase
import com.egoriku.ladyhappy.catalog.subcategory.domain.model.SubCategoryItem
import com.egoriku.ladyhappy.core.mvi.BaseViewModel
import com.egoriku.ladyhappy.extensions.cast
import com.egoriku.ladyhappy.network.ResultOf
import kotlinx.coroutines.launch

class EditSubCategoryViewModel(
        private val documentReference: String,
        private val loadSubCategoryUseCase: ILoadSubCategoryUseCase,
        private val uploadSubCategoryUseCase: IUploadSubCategoryUseCase
) : BaseViewModel<Event, State, Effect>() {

    init {
        setEvent(Event.LoadSubCategoryData)
    }

    private val currentSubCategoryItem: SubCategoryItem
        get() = currentState.editState.cast<EditState.Success>().subCategoryItem

    override fun createInitialState() = State(editState = EditState.Initial)

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.LoadSubCategoryData -> {
                viewModelScope.launch {
                    setState { copy(editState = EditState.Loading) }

                    when (val subcategoryModel = loadSubCategoryUseCase.load(documentReference = documentReference)) {
                        null -> setEffect { Effect.ShowToast(message = "Error loading") }
                        else -> setState { copy(editState = EditState.Success(subcategoryModel)) }
                    }
                }
            }
            is Event.SaveEditChanges -> {
                viewModelScope.launch {
                    when (uploadSubCategoryUseCase.upload(currentSubCategoryItem)) {
                        is ResultOf.Success -> setEffect { Effect.Exit(categoryId = currentSubCategoryItem.categoryId) }
                        is ResultOf.Failure -> setEffect { Effect.ShowToast("Error saving") }
                    }
                }
            }
            is Event.UpdatePopular -> setState {
                copy(editState = EditState.Success(currentSubCategoryItem.copy(isPopular = event.isPopular)))
            }
            is Event.UpdateTitle -> setState {
                copy(editState = EditState.Success(currentSubCategoryItem.copy(subCategoryName = event.title)))
            }
            is Event.UpdateDescription -> setState {
                copy(editState = EditState.Success(currentSubCategoryItem.copy(description = event.description)))
            }
        }
    }
}