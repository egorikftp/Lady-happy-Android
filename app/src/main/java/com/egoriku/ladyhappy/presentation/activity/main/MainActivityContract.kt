package com.egoriku.ladyhappy.presentation.activity.main

import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.presentation.base.BaseView

interface MainActivityContract {

    interface View : BaseView {
        companion object {
            const val ALL_GOODS_POSITION = 0
            const val ORDER_POSITION = 1
            const val SHARE_POSITION = 2
            const val FEEDBACK_POSITION = 3
            const val MAIN_PAGE_POSITION = 5

            const val CREATE_NEW_POST_POSITION = 4
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