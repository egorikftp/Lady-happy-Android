package com.egoriku.mainscreen.presentation.screen

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.egoriku.ladyhappy.extensions.getClassByName
import com.egoriku.ladyhappy.navigation.screen.FragmentScreen

class PostCreatorScreen : FragmentScreen() {

    override val arguments: Bundle = bundleOf()

    override val fragment = getClassByName<Fragment>("com.egoriku.ladyhappy.postcreator.PostCreatorFragment")
}