package com.egoriku.ladyhappy.presentation.fragment.allgoods

import android.support.annotation.StringRes
import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import com.egoriku.ladyhappy.presentation.base.BaseView

interface AllGoodsContract {

    interface View : BaseView {
        fun showTitle(@StringRes title: Int)

        fun render(screenModel: AllGoodsScreenModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData()
        fun onGetCategoriesSuccess(categoriesModel: CategoriesModel)
        fun onGetCategoriesFailure(e: Throwable)

        fun onGetCategoriesSuccessTracking()
        fun onGetCategoriesFailureTracking()

        fun onCategoryClickSuccessTracking()
        fun onCategoryClickFailureTracking(e: Throwable)
    }
}