package com.egoriku.ladyhappy.mainscreen.presentation.components.dynamicDelivery

import com.egoriku.ladyhappy.core.sharedmodel.params.EditParams
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams

sealed class DynamicScreen {

    data class PostCreator(
        val className: String = "com.egoriku.ladyhappy.postcreator.presentation.PostCreatorFragment",
        val params: PostCreatorParams = PostCreatorParams()
    ) : DynamicScreen()

    data class Edit(
        val className: String = "com.egoriku.ladyhappy.edit.presentation.EditFragment",
        val editParams: EditParams = EditParams()
    ) : DynamicScreen()
}