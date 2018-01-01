package com.egoriku.ladyhappy.presentation.fragment.allgoods

import android.support.annotation.StringRes
import com.egoriku.corelib_kt.arch.BaseContract
import com.egoriku.ladyhappy.domain.models.CategoriesModel
import com.egoriku.ladyhappy.domain.models.NewsModel
import com.egoriku.ladyhappy.presentation.base.BaseView

interface AllGoodsContract {

    interface View : BaseView {
        fun showTitle(@StringRes title: Int)

        fun render(screenModel: AllGoodsScreenModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData()

        fun onGetCategoriesSuccess(categoriesModel: CategoriesModel)
        fun onGetCategoriesError(e: Throwable)
        fun onGetNewsSuccess(newsModel: NewsModel)
        fun onGetNewsError(e: Throwable)

        fun onGetCategoriesSuccessTracking()
        fun onGetCategoriesErrorTracking()

        fun onGetNewsSuccessTracking()
        fun onGetNewsErrorTracking()

        fun onCategoryClickSuccessTracking()
    }
}