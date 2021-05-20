package com.egoriku.ladyhappy.extensions

import androidx.fragment.app.Fragment

inline fun <reified T : Fragment> getClassByName(className: String): T =
    checkNotNull(Class.forName(className).newInstance().castOrNull()) {
        "Fragment Class is not public or is missing public no-args constructor"
    }