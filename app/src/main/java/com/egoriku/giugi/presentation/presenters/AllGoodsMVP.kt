package com.egoriku.giugi.presentation.presenters

import com.egoriku.giugi.presentation.ui.base.BaseMvpView


interface AllGoodsMVP {

    interface View : BaseMvpView {
        fun showTitle(message: String)

        fun showCategories()
        fun showNews()
    }

    interface Presenter {
        fun getCategories()
        fun onGetCategoriesSuccess()
        fun onGetCategoriesFailure(e: Throwable)

        fun onCategoryClickSuccessTracking()
        fun onCategoryClickFailureTracking(e: Throwable)
    }
}