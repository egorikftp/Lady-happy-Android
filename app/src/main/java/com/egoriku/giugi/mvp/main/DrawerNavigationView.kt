package com.egoriku.giugi.mvp.main

import com.arellomobile.mvp.MvpView

interface DrawerNavigationView : MvpView {

    companion object {
        const val ALL_GOODS_POSITION = 0
        const val ORDER_POSITION = 1
        const val SHARE_POSITION = 2
        const val FEEDBACK_POSITION = 3
    }

    fun selectDrawerItem(position: Int)
}
