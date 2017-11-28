package com.egoriku.ladyhappy.presentation.presenters

import com.egoriku.ladyhappy.presentation.ui.base.BaseMvpView

interface MainActivityMVP {

    interface View : BaseMvpView {
        companion object {
            const val ALL_GOODS_POSITION = 0
            const val ORDER_POSITION = 1
            const val SHARE_POSITION = 2
            const val FEEDBACK_POSITION = 3

            const val CREATE_NEW_POST_POSITION = 4
        }

        fun selectDrawerItem(position: Int)
    }

    interface Presenter {
        fun openAllGoodsCategory()
        fun openOrderCategory()
        fun openShareCategory()
        fun openFeedbackCategory()
        fun openCreateNewPostScreen()
        fun onBackPressed()
    }
}