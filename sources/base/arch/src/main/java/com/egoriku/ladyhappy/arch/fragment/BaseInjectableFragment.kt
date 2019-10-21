package com.egoriku.ladyhappy.arch.fragment

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseInjectableFragment(
        layoutId: Int
) : Fragment(layoutId) {

    override fun onAttach(context: Context) {
        injectDependencies()
        super.onAttach(context)
    }

    abstract fun injectDependencies()
}