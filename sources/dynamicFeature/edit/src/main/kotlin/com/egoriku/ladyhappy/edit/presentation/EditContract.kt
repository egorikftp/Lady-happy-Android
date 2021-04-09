package com.egoriku.ladyhappy.edit.presentation

import com.egoriku.ladyhappy.core.mvi.UiEffect
import com.egoriku.ladyhappy.core.mvi.UiEvent
import com.egoriku.ladyhappy.core.mvi.UiState
import com.egoriku.ladyhappy.core.sharedmodel.domain.SubCategoryModel

sealed class Event : UiEvent {
    object SaveEditChanges : Event()
    object LoadSubCategoryData : Event()

    data class UpdatePopular(val isPopular: Boolean) : Event()
    data class UpdateTitle(val title: String) : Event()
    data class UpdateDescription(val description: String) : Event()
    data class UpdateCount(val count: Int) : Event()
}

data class State(val editState: EditState) : UiState

sealed class EditState {
    object Initial : EditState()
    object Error : EditState()
    object Loading : EditState()
    data class Success(val subCategoryModel: SubCategoryModel) : EditState()
}

sealed class Effect : UiEffect {

    data class Exit(val categoryId: Int) : Effect()
    data class ShowToast(val message: String) : Effect()
}
