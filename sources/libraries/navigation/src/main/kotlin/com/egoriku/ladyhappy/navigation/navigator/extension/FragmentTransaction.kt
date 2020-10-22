package com.egoriku.ladyhappy.navigation.navigator.extension

import android.view.View
import androidx.fragment.app.FragmentTransaction

internal fun FragmentTransaction.addToBackStackIf(
        condition: Boolean,
        tag: () -> String
): FragmentTransaction = when {
    condition -> addToBackStack(tag.invoke())
    else -> this
}

internal fun FragmentTransaction.addSharedElements(sharedElements: Map<View, String>): FragmentTransaction {
    sharedElements.forEach { (key, value) ->
        addSharedElement(key, value)
    }

    return this
}