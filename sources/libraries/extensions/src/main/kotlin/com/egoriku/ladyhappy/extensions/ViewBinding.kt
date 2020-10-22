package com.egoriku.ladyhappy.extensions

import android.content.Context
import androidx.viewbinding.ViewBinding

inline val ViewBinding.context: Context
    get() = root.context