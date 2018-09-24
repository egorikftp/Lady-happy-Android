package com.egoriku.core.di

import android.app.Activity
import android.support.v4.app.Fragment
import com.egoriku.core.IApplication

fun Fragment?.findDependencies(): ApplicationProvider {
    if (this == null) {
        throw Exception("FragmentActivity mustn't be null")
    }

    return (this.activity.applicationContext as IApplication).getAppComponent()
}

fun Activity?.findDependencies(): ApplicationProvider {
    if (this == null) {
        throw Exception("Activity mustn't be null")
    }

    return (this.applicationContext as IApplication).getAppComponent()
}