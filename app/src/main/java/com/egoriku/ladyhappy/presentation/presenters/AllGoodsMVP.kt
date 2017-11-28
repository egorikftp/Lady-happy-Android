package com.egoriku.ladyhappy.presentation.presenters

import android.support.annotation.StringRes
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import com.egoriku.ladyhappy.presentation.ui.base.BaseMvpView


interface AllGoodsMVP {

    interface View : BaseMvpView {
        fun showTitle(@StringRes title: Int)

        fun showCategories()
        fun showNews()
    }

    interface Presenter {
        fun getCategories()
        fun onGetCategoriesSuccess(categoriesModel: CategoriesModel)
        fun onGetCategoriesFailure(e: Throwable)

        fun onGetCategoriesSuccessTracking()
        fun onGetCategoriesFailureTracking()

        fun onCategoryClickSuccessTracking()
        fun onCategoryClickFailureTracking(e: Throwable)
    }
}