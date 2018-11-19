package com.egoriku.core.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.egoriku.core.IApplication

fun Fragment?.findDependencies(): ApplicationProvider {
    if (this == null) {
        throw Throwable("FragmentActivity mustn't be null")
    }

    return (activity?.applicationContext as IApplication).getAppComponent()
}

fun Activity?.findDependencies(): ApplicationProvider {
    if (this == null) {
        throw Throwable("Activity mustn't be null")
    }

    return (applicationContext as IApplication).getAppComponent()
}