package com.egoriku.featureactivitymain.presentation.activity

import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ui.BaseView

interface MainActivityContract {

    interface View : BaseView {
        companion object {
            const val ALL_GOODS_POSITION = 0
            const val ORDER_POSITION = 1
            const val SHARE_POSITION = 2
            const val FEEDBACK_POSITION = 3
            const val MAIN_PAGE_POSITION = 4
        }
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun openMainPageFragment()
        fun openAllGoodsCategory()
        fun openOrderCategory()
        fun openShareCategory()
        fun openFeedbackCategory()
        fun openCreateNewPostScreen()
        fun onBackPressed()
    }
}