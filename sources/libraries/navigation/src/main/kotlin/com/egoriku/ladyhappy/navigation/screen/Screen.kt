package com.egoriku.ladyhappy.navigation.screen

import android.content.Intent
import androidx.fragment.app.Fragment

sealed class Screen

abstract class FragmentScreen : Screen() {

    abstract val fragment: Fragment
}

abstract class ActivityScreen : Screen() {

    abstract val intent: Intent
}