package com.egoriku.ladyhappy.mainscreen.presentation.screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.sharedmodel.key.KEY_DOCUMENT_REFERENCE
import com.egoriku.ladyhappy.core.sharedmodel.params.EditParams
import com.egoriku.ladyhappy.extensions.getClassByName
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough

class EditScreen(
    private val className: String,
    private val params: EditParams
) : FragmentScreen() {

    override val fragment: Fragment
        get() = getClassByName<Fragment>(className).apply {
            arguments = bundleOf(KEY_DOCUMENT_REFERENCE to params.documentReference)

            enterTransition = MaterialFadeThrough()
            returnTransition = MaterialFade()
        }
}