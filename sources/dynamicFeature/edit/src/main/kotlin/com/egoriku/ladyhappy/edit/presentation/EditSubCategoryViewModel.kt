package com.egoriku.ladyhappy.edit.presentation

import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.mvi.BaseViewModel
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel
import com.egoriku.ladyhappy.edit.domain.ILoadSubCategoryUseCase
import com.egoriku.ladyhappy.edit.domain.IUpdateSubCategoryUseCase
import com.egoriku.ladyhappy.extensions.cast
import com.egoriku.ladyhappy.network.ResultOf
import kotlinx.coroutines.launch

class EditSubCategoryViewModel(
    private val documentReference: String,
    private val loadSubCategoryUseCase: ILoadSubCategoryUseCase,
    private val updateSubCategoryUseCase: IUpdateSubCategoryUseCase
) : BaseViewModel<Event, State, Effect>() {

    init {
        setEvent(Event.LoadSubCategoryData)
    }

    private val currentSubCategoryModel: SubCategoryModel
        get() = currentState.editState.cast<EditState.Success>().subCategoryModel

    override fun createInitialState() = State(editState = EditState.Initial)

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.LoadSubCategoryData -> viewModelScope.launch {
                setState { copy(editState = EditState.Loading) }

                when (val subcategoryModel =
                    loadSubCategoryUseCase.load(documentReference = documentReference)) {
                    null -> setEffect { Effect.ShowToast(message = "Error loading") }
                    else -> setState { copy(editState = EditState.Success(subcategoryModel)) }
                }
            }
            is Event.SaveEditChanges -> viewModelScope.launch {
                when (updateSubCategoryUseCase.upload(currentSubCategoryModel)) {
                    is ResultOf.Success -> setEffect {
                        Effect.Exit(categoryId = currentSubCategoryModel.categoryId)
                    }
                    is ResultOf.Failure -> setEffect {
                        Effect.ShowToast("Error saving")
                    }
                }
            }
            is Event.UpdatePopular -> setState {
                copy(editState = EditState.Success(currentSubCategoryModel.copy(isPopular = event.isPopular)))
            }
            is Event.UpdateTitle -> setState {
                copy(editState = EditState.Success(currentSubCategoryModel.copy(subCategoryName = event.title)))
            }
            is Event.UpdateDescription -> setState {
                copy(editState = EditState.Success(currentSubCategoryModel.copy(description = event.description)))
            }
            is Event.UpdateCount -> setState {
                copy(editState = EditState.Success(currentSubCategoryModel.copy(publishedCount = event.count)))
            }
        }
    }
}