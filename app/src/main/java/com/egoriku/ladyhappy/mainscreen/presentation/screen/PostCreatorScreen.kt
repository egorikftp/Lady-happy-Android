package com.egoriku.ladyhappy.mainscreen.presentation.screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.core.sharedmodel.key.KEY_POST_CREATOR_EXTRA
import com.egoriku.ladyhappy.core.sharedmodel.params.PostCreatorParams
import com.egoriku.ladyhappy.extensions.getClassByName
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen
import com.google.android.material.transition.MaterialSharedAxis

class PostCreatorScreen(
    private val className: String,
    private val params: PostCreatorParams
) : FragmentScreen() {

    override val fragment: Fragment
        get() = getClassByName<Fragment>(className).apply {
            arguments = bundleOf(KEY_POST_CREATOR_EXTRA to params)
            enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
            returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        }
}