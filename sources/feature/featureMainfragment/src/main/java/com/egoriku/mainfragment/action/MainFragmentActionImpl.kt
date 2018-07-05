package com.egoriku.mainfragment.action

import android.support.v4.app.Fragment
import com.egoriku.core.actions.MainFragmentAction
import com.egoriku.mainfragment.presentation.fragment.MainPageFragment

class MainFragmentActionImpl : MainFragmentAction {

    override fun provideFragment(): Fragment = MainPageFragment.newInstance()
}