package com.egoriku.ladyhappy.catalog.subcategory.presentation.screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.catalog.edit.presentation.ARGUMENT_DOCUMENT_REFERENCE
import com.egoriku.ladyhappy.catalog.edit.presentation.EditSubCategoryFragment
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough

class EditSubCategoryScreen(
        private val documentReference: String
) : FragmentScreen() {

    override val fragment: Fragment
        get() = EditSubCategoryFragment().apply {
            enterTransition = MaterialFadeThrough()
            returnTransition = MaterialFade()

            arguments = bundleOf(ARGUMENT_DOCUMENT_REFERENCE to documentReference)
        }
}