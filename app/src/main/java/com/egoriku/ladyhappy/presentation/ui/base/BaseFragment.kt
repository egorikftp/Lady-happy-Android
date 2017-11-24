package com.egoriku.ladyhappy.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View


abstract class BaseFragment : Fragment(), BaseView {

    fun getFragmentActivity(): FragmentActivity? {
        return activity
    }

    fun getFragment(): Fragment {
        return this
    }

    abstract fun getArgs(_bundle: Bundle?)

    override fun getContext(): Context? {
        return activity
    }

    interface OnShowMessageListener {
        fun onShowMessage(message: String)
    }

    interface OnChangeFragment {
        fun onChangeFragment(fragmentTag: String,
                             @Nullable sharedElement: Pair<View, String>)
    }
}