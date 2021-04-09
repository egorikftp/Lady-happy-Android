package com.egoriku.ladyhappy.extensions

import android.content.Context
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

inline val ViewBinding.context: Context
    get() = root.context

inline val ViewBinding.inflater: LayoutInflater
    get() = context.inflater()